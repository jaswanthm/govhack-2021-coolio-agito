package xyz.jaswanth.hackathon.getweather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.jaswanth.hackathon.getweather.model.WeatherData;

public interface WeatherApi {
        @GET("data/2.5/weather?units=metric")
        Call<WeatherData> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);
}