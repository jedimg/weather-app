package com.example.laptop.weathertestapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laptop.weathertestapp.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{

    private List<com.example.laptop.weathertestapp.model.List> weather_list;
    private int row_weather;
    private Context applicationContext;

    public WeatherAdapter (List<com.example.laptop.weathertestapp.model.List> weather_list,
                           int row_weather, Context applicationContext){

        this.weather_list = weather_list;
        this.row_weather = row_weather;
        this.applicationContext = applicationContext;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(row_weather, parent, false);

        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        Double temp_temp = ((weather_list.get(position).getMain().getTemp()) - 273.15);
        temp_temp = BigDecimal.valueOf(temp_temp)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        holder.tvDate.setText(weather_list.get(position).getDtTxt());
        holder.tvTemp.setText(String.valueOf(temp_temp) + " °C");
        holder.tvWeatherMain.setText(weather_list.get(position).getWeather().get(0).getMain());
        holder.tvWeatherDesc.setText(weather_list.get(position).getWeather().get(0).getDescription());

    }

    @Override
    public int getItemCount() {
        return weather_list.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvDate) TextView tvDate;
        @BindView(R.id.tvTemp) TextView tvTemp;
        @BindView(R.id.tvWeatherMain) TextView tvWeatherMain;
        @BindView(R.id.tvWeatherDesc) TextView tvWeatherDesc;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(itemView);
        }
    }

}
