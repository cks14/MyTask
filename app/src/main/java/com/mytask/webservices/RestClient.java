package com.mytask.webservices;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mytask.webservices.model.Login;
import com.mytask.webservices.model.Product;
import com.mytask.webservices.model.Signup;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by satyendra on 02/10/2019.
 */

public class RestClient {

    private static RestClient mInstance = new RestClient();
    OkHttpClient client;
    Retrofit retrofit;
    private RestService mRestService;


    private RestClient() {
    }
    public static RestClient getInst() {
        return mInstance;
    }

    public void setup() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addNetworkInterceptor((Interceptor) new StethoInterceptor());

        // Should be used only in Debug Mode.
        if (true) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(true ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE); //// TODO: 21-07-2016
            builder.addInterceptor(interceptor);
        }

        client = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.IDECORAMA_HOST)
                .client(client)
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = retrofit.create(RestService.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Call<Signup> Signup(String fld_device_id, String fld_user_name, String fld_user_email, String fld_user_phone, String fld_user_password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fld_device_id", fld_device_id);
        hashMap.put("fld_user_name", fld_user_name);
        hashMap.put("fld_user_email", fld_user_email);
        hashMap.put("fld_user_phone", fld_user_phone);
        hashMap.put("fld_user_password", fld_user_password);
        return mRestService.Signup(hashMap);
    }

    public Call<Login> Login(String fld_user_phone, String fld_user_password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fld_user_phone", fld_user_phone);
        hashMap.put("fld_user_password", fld_user_password);
        return mRestService.Login(hashMap);
    }

    public Call<Product> Product(String fld_action_type, String fld_brand_id, String cart_total,String fld_cat_id,String fld_search_txt,
                                 String fld_total_page,String fld_user_id,
                                 String grid_list_view,String fld_max_price,String next_page,String fld_page_no,String fld_pincode,
                                 String fld_spcl_price,String fld_product_price,String fld_product_qty,String shipping_total,
                                 String statusCode,String total_seller_count) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fld_action_type", fld_action_type);
        hashMap.put("fld_brand_id", fld_brand_id);
        hashMap.put("cart_total", cart_total);
        hashMap.put("fld_cat_id", fld_cat_id);
        hashMap.put("fld_search_txt", fld_search_txt);
        hashMap.put("fld_total_page", fld_total_page);
        hashMap.put("fld_user_id", fld_user_id);
        hashMap.put("grid_list_view", grid_list_view);
        hashMap.put("fld_max_price", fld_max_price);
        hashMap.put("next_page", next_page);
        hashMap.put("fld_page_no", fld_page_no);
        hashMap.put("fld_pincode", fld_pincode);
        hashMap.put("fld_spcl_price", fld_spcl_price);
        hashMap.put("fld_product_price", fld_product_price);
        hashMap.put("fld_product_qty", fld_product_qty);
        hashMap.put("shipping_total", shipping_total);
        hashMap.put("statusCode", statusCode);
        hashMap.put("total_seller_count", total_seller_count);
        return mRestService.Product(hashMap);
    }

}

