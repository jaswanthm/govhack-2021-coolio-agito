package xyz.jaswanth.hackathon;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<LatLngPair> latLngPairs = new ArrayList<LatLngPair>();
        LatLngPair latLngPairJas = new LatLngPair(new LatLng(-38.118562, 145.336217), new LatLng(-38.109089, 145.336057), "Jas' Coffee Walk", "Discover the local architecture via the winding backstreets");
        LatLngPair latLngPairLucy = new LatLngPair(new LatLng(-37.817544, 144.956032), new LatLng(-37.823327, 144.958117), "Lucy in the sky", "Routes with a view and notable spots for sunsets");
        LatLngPair latLngPairAnki = new LatLngPair(new LatLng(-37.817544, 144.956032), new LatLng(-37.823327, 144.958117), "Anki is out for a stroll", "Discover the local architecture via the winding backstreets");
        LatLngPair latLngPairKristian = new LatLngPair(new LatLng(-37.817544, 144.956032), new LatLng(-37.823327, 144.958117), "Kristian sneaks out", "Discover the local architecture via the winding backstreets");

        latLngPairs.add(latLngPairJas);
        latLngPairs.add(latLngPairLucy);
        latLngPairs.add(latLngPairAnki);
        latLngPairs.add(latLngPairKristian);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(latLngPairs);
        recyclerView.setAdapter(mAdapter);
    }
    // ...
}