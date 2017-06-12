package com.example.laptop.weathertestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laptop.weathertestapp.adapter.WeatherAdapter;
import com.example.laptop.weathertestapp.injection.MyApp;
import com.example.laptop.weathertestapp.model.List;
import com.example.laptop.weathertestapp.presenter.WeatherContract;
import com.example.laptop.weathertestapp.presenter.WeatherPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements WeatherContract.IWeatherView {

    @Inject WeatherPresenter presenter;

    Unbinder unbinder;

    @BindView(R.id.rvWeather) RecyclerView rvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        ((MyApp)getApplication()).getWeather_component().inject(this);

        presenter.bind(this);
        initialiseRecyclerView();
        presenter.fetchWeather();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.unbind();
    }

    private void initialiseRecyclerView() {
        rvWeather.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void receiveWeather(java.util.List<List> weather_list) {

        rvWeather.setAdapter(new WeatherAdapter(weather_list, R.layout.row_weather, getApplicationContext()));
    }
}
