package com.example.assginment.network;

import com.example.assginment.model.BigData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String SEVER = "https://springfilm.herokuapp.com";

    @GET("/apifree/home")
    Call<BigData> apiGetBigData();
}
