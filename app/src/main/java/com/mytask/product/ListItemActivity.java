package com.mytask.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.mytask.R;
import com.mytask.util.LocalConstant;
import com.mytask.webservices.HttpCallback;
import com.mytask.webservices.RestClient;
import com.mytask.webservices.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class ListItemActivity extends AppCompatActivity {

    private ShopByCategoryAdapter shopByCategoryAdapter;
    List<Product.ProductDataBean> products = new ArrayList<>();
    RecyclerView recycler_offers_product;
    int numberofcolumns = 2;
    private int currentPage = 1;
    private boolean isLastPage = false;
    private int TOTAL_PAGES ;

    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        initView();
    }

    private void initView() {

        recycler_offers_product = findViewById(R.id.recycler_offers_product);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(ListItemActivity.this);
        recycler_offers_product.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberofcolumns));
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        shopByCategoryAdapter = new ShopByCategoryAdapter(ListItemActivity.this,products);
        recycler_offers_product.setAdapter(shopByCategoryAdapter);

        VideoWebServices();

        recycler_offers_product.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                //dialogLoader.showProgressDialog();
                currentPage++;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        VideoWebServices();
                    }
                }, 0000);
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });


    }

    private void VideoWebServices() {

        RestClient.getInst().Product("", "", "",
                "216", "", "",
                LocalConstant.getInstance(ListItemActivity.this).getUserID()
                , "", "", "", "",
                "", "", "",
                "", ""
                , "", "").enqueue(new HttpCallback<Product>() {
            @Override
            public void onSuccess(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    
                    if (response.body().getMessage().equalsIgnoreCase(" More Products available")) {
                        LocalConstant.getInstance(ListItemActivity.this).setIsLogin("1");

                        List<Product.ProductDataBean> items = new ArrayList<>();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Product.ProductDataBean postItem = null;
                                System.out.println("SIZE11===>"+response.body().getProduct_data().size());
                                for(int i=0;i<response.body().getProduct_data().size();i++) {
                                    postItem = response.body().getProduct_data().get(i);
                                    items.add(postItem);
                                }


                                if (currentPage != 1) shopByCategoryAdapter.removeLoading();
                                shopByCategoryAdapter.addItems(items);

                                TOTAL_PAGES = response.body().getFld_total_page();

                                if (currentPage <= TOTAL_PAGES) {
                                    shopByCategoryAdapter.addLoading();
                                } else {
                                    isLastPage = true;
                                }


                            }
                        },0);


                    }
                }

            }

            @Override
            public void onError(Call<Product> call, Throwable t) {

            }
        });

    }
}