package com.example.laptop.weathertestapp.presenter;


import java.util.List;

public interface WeatherContract {

    interface IWeatherPresenter{

        void bind(IWeatherView view);
        void unbind();
        void fetchWeather();
    }

    interface IWeatherView{

        void receiveWeather(List<com.example.laptop.weathertestapp.model.List> weather_list);
    }
}
