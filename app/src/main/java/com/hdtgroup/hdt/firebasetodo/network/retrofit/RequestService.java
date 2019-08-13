package com.hdtgroup.hdt.firebasetodo.network.retrofit;

import com.hdtgroup.hdt.firebasetodo.entities.HttpResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestService {
    @Headers("Content-Type:application/json")
    @POST("send")
    Call<HttpResponse> postRequest(@Header("Authorization") String authorization, @Body HashMap<String, Object> request);
}
