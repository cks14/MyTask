package com.mytask.product;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mytask.R;
import com.mytask.webservices.model.Product;

import java.util.List;

public class ShopByCategoryAdapter extends RecyclerView.Adapter<ShopByCategoryAdapter.ViewHolder> {
    Context mContext;
    List<Product.ProductDataBean>products;

    public ShopByCategoryAdapter(Context context, List<Product.ProductDataBean> products) {
        this.mContext = context;
        this.products = products;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView categoryImage;
        public TextView categoryName,price,offprice,offpersprice;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage  = (ImageView) itemView.findViewById(R.id.categoryImage);
            categoryName  = (TextView) itemView.findViewById(R.id.categoryName);
            price  = (TextView) itemView.findViewById(R.id.price);
            offprice  = (TextView) itemView.findViewById(R.id.offprice);
            offpersprice  = (TextView) itemView.findViewById(R.id.offpersprice);

        }

        @Override
        public void onClick(View v) {


        }

        public void setData() {

            if(products.get(getAdapterPosition()).getName()!=null && !products.get(getAdapterPosition()).getName().isEmpty()) {
                categoryName.setText(Html.fromHtml(products.get(getAdapterPosition()).getName()));
            }

            if(products.get(getAdapterPosition()).getSpcl_price()!=null && !products.get(getAdapterPosition()).getSpcl_price().isEmpty()) {
                price.setText(Html.fromHtml("₹"+products.get(getAdapterPosition()).getSpcl_price()));
            }

            if(products.get(getAdapterPosition()).getPrice()!=null && !products.get(getAdapterPosition()).getPrice().isEmpty()) {
                offprice.setPaintFlags(offprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                offprice.setText(Html.fromHtml("₹"+products.get(getAdapterPosition()).getPrice()));
            }

            if(products.get(getAdapterPosition()).getExtra_price()!=null && !products.get(getAdapterPosition()).getExtra_price().isEmpty()) {
                offpersprice.setText(Html.fromHtml(products.get(getAdapterPosition()).getExtra_price()));
            }
            if(products.get(getAdapterPosition()).getDefault_image() != null && !products.get(getAdapterPosition()).getDefault_image().isEmpty()) {
                
                Glide
                        .with(mContext)
                        .load(products.get(getAdapterPosition()).getDefault_image())
                        .into(categoryImage);

            }

        }

        public void setAction() {

        }
    }
    private Double calPercentage(double parseDouble, double parseDouble1) {

        Double percentage = ((parseDouble1 - parseDouble) / parseDouble1) * 100;

        return percentage;
    }


    @Override
    public int getItemCount() {
      //  return products.size();

        int i = 0;
        try {
            i = products.size();
        } catch (Exception e) {
            i = 0;
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public ShopByCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.layout_category_product, parent, false);
            return new ShopByCategoryAdapter.ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public void addItems(List<Product.ProductDataBean> postItems) {
        products.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        products.add(new Product.ProductDataBean());
        notifyItemInserted(products.size() - 1);
    }
    public void removeLoading() {
        int position = products.size() - 1;
        Product.ProductDataBean item = getItem(position);
        if (item != null) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        products.clear();
        notifyDataSetChanged();
    }

    Product.ProductDataBean getItem(int position) {
        try {
            return products.get(position);
        }catch (Exception e){
            return products.get(position);
        }

    }

    @Override
    public void onBindViewHolder(final ShopByCategoryAdapter.ViewHolder holder, final int position) {

        ((ShopByCategoryAdapter.ViewHolder) holder).setData();
        ((ShopByCategoryAdapter.ViewHolder) holder).setAction();

    }
}