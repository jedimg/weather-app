package com.example.laptop.weathertestapp.injection;


import com.example.laptop.weathertestapp.MainActivity;

import dagger.Component;

@Component(dependencies = Weather_Module.class)
public interface Weather_Component {

    void inject(MainActivity mainActivity);
}
