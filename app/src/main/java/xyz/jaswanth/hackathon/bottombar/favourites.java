package xyz.jaswanth.hackathon.bottombar;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.jaswanth.hackathon.OneStopActivity;
import xyz.jaswanth.hackathon.R;
import xyz.jaswanth.hackathon.covid.CovidApi;
import xyz.jaswanth.hackathon.covid.model.CovidResponse;
import xyz.jaswanth.hackathon.crowd.CrowdApi;
import xyz.jaswanth.hackathon.crowd.model.CrowdResponse;
import xyz.jaswanth.hackathon.getweather.WeatherApi;
import xyz.jaswanth.hackathon.getweather.model.WeatherData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favourites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favourites extends Fragment {

    String postCode = "3000";
    String address = "99 King Street";
    Double lat = -37.81757761596491;
    Double lng = 144.9560096858819;
    String API_KEY = "";

    WeatherData weatherResponse;

    TextView weatherIndicator;
    TextView covidIndicator;
    TextView crowdIndicator;

    Button covidButton;
    Button weatherButton;
    Button crowdButton;

    ArrayList<String> covidDetails = new ArrayList<>();
    ArrayList<String> weatherDetails = new ArrayList<>();
    ArrayList<String> crowdDetails = new ArrayList<>();

    AlertDialog.Builder builderSingle;

    ImageView weatherImage;

    public static String BaseUrl = "https://api.openweathermap.org/";
    public static String AppId = "2e65127e909e178d0af311a81f39948c";

    public static String BaseUrlCovid = "https://discover.data.vic.gov.au/";

    public static String BaseUrlCrowd = "https://ridespace.coronavirus.vic.gov.au/";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favourites() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favourites.
     */
    // TODO: Rename and change types and number of parameters
    public static favourites newInstance(String param1, String param2) {
        favourites fragment = new favourites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
//        location = (Button) view.findViewById(R.id.etlocation);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        setSuburb(sharedPref.getString("postcode", "3000"));

        weatherImage = (ImageView) view.findViewById(R.id.image_weather);
        weatherIndicator = (TextView) view.findViewById(R.id.indicator_weather);
        covidIndicator = (TextView) view.findViewById(R.id.indicator_covid_cases);
        crowdIndicator = (TextView) view.findViewById(R.id.indicator_crowd_reader);

        covidButton = (Button) view.findViewById(R.id.button_info_covid);
        weatherButton = (Button) view.findViewById(R.id.button_info_weather);
        crowdButton = (Button) view.findViewById(R.id.button_info_crowd_reader);

        covidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Covid Cases", covidDetails);
                builderSingle.show();
            }
        });

        crowdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Crowd Cases", crowdDetails);
                builderSingle.show();
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Weather Details", weatherDetails);
                builderSingle.show();
            }
        });

//        // Register for broadcasts when a device is discovered.
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        registerReceiver(receiver, filter);

        getWeatherData();
        getCovidData();
        getCrowdData();
        return view;
    }

    public void setSuburb(String postCode) {
        this.postCode = postCode;
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("postcode", postCode);
        editor.apply();
    }

    public void refresh() {
        getWeatherData();
        getCovidData();
        getCrowdData();
    }

    void getWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherData> call = service.getCurrentWeatherData(lat.toString(), lng.toString(), AppId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    WeatherData weatherResponse = (WeatherData) response.body();
                    assert weatherResponse != null;

                    Log.e("Weather", weatherResponse.toString());
//                    Toast.makeText(getActivity().getApplicationContext(), weatherResponse.getMain().getTemp().toString(), Toast.LENGTH_SHORT).show();

                    String icon = weatherResponse.getWeather().get(0).getIcon();
                    String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";

                    Picasso.get().load(iconUrl).into(weatherImage);
                    weatherIndicator.setText(weatherResponse.getMain().getTemp().toString() + "°C");

                    ArrayList<String> data = new ArrayList<>();
                    data.add("Temperature is " + weatherResponse.getMain().getTemp() + "°C");
                    data.add("Feels like " + weatherResponse.getMain().getFeelsLike() + "°C");
                    data.add("Humidity is " + weatherResponse.getMain().getHumidity());
                    data.add("Pressure is " + weatherResponse.getMain().getPressure());
                    data.add("Wind speed is " + weatherResponse.getWind().getSpeed().toString());

                    weatherDetails = data;

                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.e("Weather", t.getMessage());
            }
        });
    }

    void getCovidData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlCovid)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi service = retrofit.create(CovidApi.class);
        Call<CovidResponse> call = service.getCovidData(postCode);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    CovidResponse covidResponse = (CovidResponse) response.body();
                    assert covidResponse != null;

                    Log.e("Covid", covidResponse.toString());
//                    Toast.makeText(getActivity().getApplicationContext(), covidResponse.getResult().getRecords().get(0).getNew(), Toast.LENGTH_SHORT).show();

                    covidIndicator.setText(covidResponse.getResult().getRecords().get(0).getNew() + " case(s)");

                    ArrayList<String> data = new ArrayList<>();
                    data.add(covidResponse.getResult().getRecords().get(0).getActive() + " active cases");
                    data.add(covidResponse.getResult().getRecords().get(0).getNew() + "new cases");
                    data.add("Current postcode is " + covidResponse.getResult().getRecords().get(0).getPostcode());
                    data.add("Rate is " + covidResponse.getResult().getRecords().get(0).getRate());
                    data.add("Rank is " + covidResponse.getResult().getRecords().get(0).getRank().toString());
                    data.add("Population is " + covidResponse.getResult().getRecords().get(0).getPopulation());

                    covidDetails = data;
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.e("Weather", t.getMessage());
            }
        });
    }

    void getCrowdData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlCrowd)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrowdApi service = retrofit.create(CrowdApi.class);
        Call<List<CrowdResponse>> call = service.getCrowdData(lat, lng, 500);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    List<CrowdResponse> crowdResponses = (List<CrowdResponse>) response.body();
                    assert crowdResponses != null;

                    Log.e("Crowd", crowdResponses.toString());
//                    Toast.makeText(getActivity().getApplicationContext(), crowdResponses.get(0).getCapacity() , Toast.LENGTH_SHORT).show();

                    crowdIndicator.setText(crowdResponses.get(0).getCapacity());

                    ArrayList<String> data = new ArrayList<>();
                    data.add("Crowd based on " + crowdResponses.get(0).getName());
                    data.add("Crowd Status is " + crowdResponses.get(0).getCapacity());
                    data.add("Crowd class is " + crowdResponses.get(0).getCapacityClass());
                    data.add("Crowd label is " + crowdResponses.get(0).getLabel());

                    crowdDetails = data;

                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.e("Weather", t.getMessage());
            }
        });
    }

    public void showDialog(String title, ArrayList<String> list) {
        builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setIcon(R.drawable.ic_splash);
        builderSingle.setTitle(title);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_dialog);
        final int size = list.size();
        for (int i = 0; i < size; i++)
        {
            arrayAdapter.add(list.get(i));
        }

        builderSingle.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
//                builderInner.setMessage(strName);
//                builderInner.setTitle("Your Selected Item is");
//                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog,int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builderInner.show();
            }
        });
    }
}