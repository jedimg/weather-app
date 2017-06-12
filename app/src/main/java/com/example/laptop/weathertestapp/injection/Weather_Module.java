package com.example.laptop.weathertestapp.injection;


import com.example.laptop.weathertestapp.service.Interacter;
import com.example.laptop.weathertestapp.service.WeatherService;

import dagger.Module;
import dagger.Provides;

@Module
public class Weather_Module {

    @Provides
    public Interacter getInteracterObject(){

        return new WeatherService();
    }
}
