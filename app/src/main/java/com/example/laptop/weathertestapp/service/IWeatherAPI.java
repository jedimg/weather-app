package com.example.laptop.weathertestapp.service;

import com.example.laptop.weathertestapp.constants.Constants;
import com.example.laptop.weathertestapp.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by laptop on 12/06/2017.
 */

public interface IWeatherAPI {

    @GET(Constants.END_URL)
    Observable<Result> getResult(@Query("id") Integer city_id,
                                 @Query("APPID") String api_key);
}
