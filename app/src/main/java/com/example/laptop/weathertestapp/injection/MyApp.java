package com.example.laptop.weathertestapp.injection;

import android.app.Application;

public class MyApp extends Application{

    Weather_Component weather_component;

    public Weather_Component getWeather_component(){
        return weather_component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        weather_component = DaggerWeather_Component.create();
    }
}
