package xyz.jaswanth.hackathon.covid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.jaswanth.hackathon.covid.model.CovidResponse;
import xyz.jaswanth.hackathon.getweather.model.WeatherData;

public interface CovidApi {
        @GET("api/3/action/datastore_search?resource_id=e3c72a49-6752-4158-82e6-116bea8f55c8")
        Call<CovidResponse> getCovidData(@Query("q") String query);
}