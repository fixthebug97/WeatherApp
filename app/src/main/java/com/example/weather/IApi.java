package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface IApi {


   @GET("/data/2.5/weather")
    Call<DATA> getData(
@Query("q") String city,
@Query("units") String unit,
@Query("appid") String appid

   );

}
