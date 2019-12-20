package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("users")
    Call<StackApiResponse> getUsers
            (@Query("page") int page,
             @Query("pagesize") int pagesize,
             @Query("site") String site);
}


