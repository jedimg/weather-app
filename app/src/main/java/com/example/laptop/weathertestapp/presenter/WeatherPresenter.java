package com.example.laptop.weathertestapp.presenter;


import com.example.laptop.weathertestapp.constants.Constants;
import com.example.laptop.weathertestapp.model.Result;
import com.example.laptop.weathertestapp.service.Interacter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter implements WeatherContract.IWeatherPresenter{

    private Interacter interacter;
    private WeatherContract.IWeatherView weatherView;
    private CompositeDisposable compositeDisposable;

    @Inject
    public WeatherPresenter(Interacter interacter){
        this.interacter = interacter;
    }

    @Override
    public void bind(WeatherContract.IWeatherView view) {
        this.weatherView = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unbind() {
        this.weatherView = null;
        this.compositeDisposable.dispose();
    }

    @Override
    public void fetchWeather() {

        compositeDisposable.add(interacter.getResultUseCase(Constants.CITY_ID, Constants.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Result>() {

                    @Override
                    public void onNext(Result result) {

                        weatherView.receiveWeather(result.getList());
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }
}
