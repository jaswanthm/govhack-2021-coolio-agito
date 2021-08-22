package xyz.jaswanth.hackathon.crowd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.jaswanth.hackathon.covid.model.CovidResponse;
import xyz.jaswanth.hackathon.crowd.model.CrowdResponse;

public interface CrowdApi {
        @GET("api/stop/near/{lat},{lng},{radius}")
        Call<List<CrowdResponse>> getCrowdData(@Path("lat") Double lat, @Path("lng") Double lng, @Path("radius") Integer radius);
}