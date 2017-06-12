package com.example.laptop.weathertestapp.service;


import com.example.laptop.weathertestapp.model.Result;

import io.reactivex.Observable;

public interface Interacter {

    Observable<Result> getResultUseCase(Integer city_id, String api_key);
}
