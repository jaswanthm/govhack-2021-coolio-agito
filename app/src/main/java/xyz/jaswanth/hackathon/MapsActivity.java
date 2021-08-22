package xyz.jaswanth.hackathon;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.jaswanth.hackathon.directionhelpers.FetchURL;
import xyz.jaswanth.hackathon.directionhelpers.LatLngInterpolator;
import xyz.jaswanth.hackathon.directionhelpers.MarkerAnimations;
import xyz.jaswanth.hackathon.directionhelpers.TaskLoadedCallback;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2, currentLocationMarkerOptions;
    private Marker currentMarker;
    Button getDirection, walk;
    private Polyline currentPolyline;
    private LatLng currentLocation;
    int count = 0;
    float hue = 120;  //(Range: 0 to 360)
    List<LatLng> originalPoints;
    List<PatternItem> pattern = Arrays.asList(
            new Dot(), new Gap(20), new Dot(), new Gap(20));

    TextView points;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        LatLngPair latLngPair = new LatLngPair(new LatLng(-37.817544, 144.956032),new LatLng(-37.823327, 144.958117), "Default", "Default");
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            latLngPair = (LatLngPair) extras.getParcelable("latlng");
            // and get whatever type user account id is
            myToolbar.setTitle(latLngPair.title);
        }
        setSupportActionBar(myToolbar);


        points = (TextView) findViewById(R.id.points);
        points.setText("0 Points");

        place1 = new MarkerOptions().position(latLngPair.from).title("Home").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.home));
        place2 = new MarkerOptions().position(latLngPair.to).title("Destination").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.finish));

        currentLocation = latLngPair.from;

        currentLocationMarkerOptions = new MarkerOptions().position(currentLocation).title("Me").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.mask_emoji));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);
    }

    public void updateCurrentLocation(LatLng latLng) {
        MarkerAnimations.animateMarkerToGB(currentMarker, latLng, new LatLngInterpolator.Spherical());
        currentPolyline.remove();
        originalPoints.remove(0);
        count++;

        points.setText(count * 10 + " Points");


        PolylineOptions options = new PolylineOptions();
        options.addAll(originalPoints);
        options.color(getResources().getColor(R.color.colorAccent));
        currentPolyline = mMap.addPolyline(options);
        currentPolyline.setPattern(pattern);
        currentPolyline.setWidth(40);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("Hackathon", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Hackathon", "Can't find style. Error: ", e);
        }

        Log.d("mylog", "Added Markers");
        mMap.addMarker(place1);
        mMap.addMarker(place2);
        currentMarker = mMap.addMarker(currentLocationMarkerOptions);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLocation)      // Sets the center of the map to Mountain View
                .zoom(16)                   // Sets the zoom
                .bearing(180)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "walking"), "walking");


    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        updatePolyLine(values);

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds


        handler.postDelayed(new Runnable(){
            public void run(){
                if(originalPoints != null && originalPoints.size() > 0)
                    updateCurrentLocation(originalPoints.get(0));

                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void updatePolyLine(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();

        PolylineOptions options = (PolylineOptions) values[0];
        options.color(getResources().getColor(R.color.colorAccent));
        currentPolyline = mMap.addPolyline(options);

        originalPoints =  new ArrayList<LatLng>(currentPolyline.getPoints());

        List<PatternItem> pattern = Arrays.asList(
                new Dash(20), new Gap(20), new Dash(20), new Gap(20));
        currentPolyline.setPattern(pattern);
        currentPolyline.setWidth(40);

    }
}
