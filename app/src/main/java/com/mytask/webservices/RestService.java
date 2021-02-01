package com.mytask.webservices;

import com.mytask.webservices.model.Login;
import com.mytask.webservices.model.Product;
import com.mytask.webservices.model.Signup;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestService {

    @POST("signup")
    Call<Signup> Signup(@Body HashMap<String, String> map);

    @POST("jlogin")
    Call<Login> Login(@Body HashMap<String, String> map);

    @POST("product")
    Call<Product> Product(@Body HashMap<String, String> map);

}


