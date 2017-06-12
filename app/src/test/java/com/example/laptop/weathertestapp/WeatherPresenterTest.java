package com.example.laptop.weathertestapp;

import com.example.laptop.weathertestapp.constants.Constants;
import com.example.laptop.weathertestapp.model.List;
import com.example.laptop.weathertestapp.model.Result;
import com.example.laptop.weathertestapp.presenter.WeatherContract;
import com.example.laptop.weathertestapp.presenter.WeatherPresenter;
import com.example.laptop.weathertestapp.service.IWeatherAPI;
import com.example.laptop.weathertestapp.service.Interacter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherPresenterTest {

    @Mock
    @Inject
    IWeatherAPI iWeatherAPI;

    @Mock
    WeatherContract.IWeatherView iWeatherView;

    @Mock
    Interacter interacter;

    @InjectMocks
    WeatherPresenter presenter;

    @Mock
    List list;

    @Mock
    java.util.List<List> lists;

    @Mock
    Result result;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        iWeatherView = mock(WeatherContract.IWeatherView.class);

        presenter = new WeatherPresenter(interacter);

        list = new List();

        lists = new ArrayList<>();
        lists.add(list);

        result = new Result();
        result.setList(lists);
    }

    @After
    public void tearDown() throws Exception{

        RxAndroidPlugins.reset();
    }

    @Test
    public void testWeatherPresenter() throws Exception {

        when(interacter.getResultUseCase(Constants.CITY_ID, Constants.API_KEY))
                .thenReturn(Observable.just(result));

        presenter.bind(iWeatherView);
        presenter.fetchWeather();
        presenter.unbind();

        Mockito.verify(iWeatherView).receiveWeather(lists);
    }


}


