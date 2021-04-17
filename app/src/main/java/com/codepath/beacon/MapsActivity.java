package com.codepath.beacon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText editTitle;
    private EditText editDescription;
    private FloatingActionButton createPinButton;
    public static final String TAG = "MapsActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    final LatLng sydney = new LatLng(-34, 151);
    private FusedLocationProviderClient mFusedLocationClient;
    private boolean mLocationPermissionGranted = false;
    public static final int ERROR_DIALOG_REQUEST = 9001;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9002;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9003;
    public static final String filename = "geoPointFile";
    private static ParseGeoPoint pinGeoPoint;
    private View view;
    LatLng currentPos;
    final FragmentManager fragmentManager = getFragmentManager();
    private RelativeLayout rlMaps;
    private static String pinName;
    private static String pinCaption;
    private static final String userID = "qaWDT0oGk5";
    private List<Pin> allPins;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */

    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createPinButton = view.findViewById(R.id.addPinButton);

    }

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_maps, container, false);
        editTitle = view.findViewById(R.id.editTitle);
        editDescription = view.findViewById(R.id.editDescription);
        createPinButton = view.findViewById(R.id.addPinButton);
        rlMaps = view.findViewById(R.id.rlMaps);
        createPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTitle.getText().toString() == null){
                    Toast.makeText(getContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String pinName = editTitle.getText().toString();
                }
                if (currentPos == null){
                    Toast.makeText(getContext(), "Error Getting Pin Location - Please Select a Location on the Map", Toast.LENGTH_SHORT).show();
                }
                else{
                    pinGeoPoint = new ParseGeoPoint(currentPos.latitude, currentPos.longitude);
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    pinName = editTitle.getText().toString();
                    pinCaption = editDescription.getText().toString();
                    //Pin newPin = new Pin();
                    //newPin.createObject(pinName, pinCaption, currentUser, pinGeoPoint);
                    createObject(pinName, pinCaption, currentUser, pinGeoPoint);
                    //savePin(pinName, pinCaption, currentUser, pinGeoPoint);
                }


            }
        });
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                /*
                if (allPins != null || allPins.size() != 0){
                    for (Pin newPin : allPins){
                        MarkerOptions marketOption = new MarkerOptions();
                        ParseGeoPoint currentPinGeoPoint = newPin.getPinGeoPoint();
                        marketOption.position(new LatLng(currentPinGeoPoint.getLatitude(), currentPinGeoPoint.getLongitude()));
                        marketOption.title(newPin.getPinName());
                        googleMap.addMarker(marketOption);
                    }
                }

                 */
                MarkerOptions markerOption1 = new MarkerOptions();
                LatLng latLng1 = new LatLng(40.45540109465747,-79.97976198792459);
                markerOption1.position(latLng1);
                markerOption1.title("Test Pin 2");
                googleMap.addMarker(markerOption1);
                MarkerOptions markerOption2 = new MarkerOptions();
                LatLng latLng2 = new LatLng(40.4410651425909, -80.00819340348244);
                markerOption2.position(latLng2);
                markerOption2.title("Picnic Spot");
                googleMap.addMarker(markerOption2);
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        currentPos = latLng;
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        //CreatePinActivity createPinActivity = new CreatePinActivity();
                        //createPinActivity.updatePinLocation(latLng);
                        // Try Saving to External Media then Read in CreatePinActivity;
                        //try (FileOutputStream fos = getContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
                        //    fos.write();
                        //}
                        /*
                          try {
                            File.createTempFile(filename, null, getContext().getCacheDir());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                         */
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        googleMap.addMarker(markerOptions);
                        getLastLocation();
                    }
                });
            }
        });
        return view;
    }



    private void readObject() {
        ParseQuery<Pin> query = ParseQuery.getQuery("Pin");
        query.include(userID);
        query.findInBackground(new FindCallback<Pin>() {
            @Override
            public void done(List<Pin> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting pins", e);
                    return;
                }
                for (Pin pin : objects){
                }
                allPins.addAll(objects);
        }
        });

        }
    private void getLastLocation() {
        Log.d(TAG, "getLastLocation called");
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    LatLng currentPos = new LatLng(location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "onComplete: lat:" + location.getLatitude() + "onComplete: long:" + location.getLongitude());

                }
            }
        });
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getLastLocation();
        } else {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

     */

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        getLastLocation();
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPos));
        /*
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        */

        /*
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markertitle = marker.getTitle();
                Intent i = new Intent(MapsActivity.this, CreatePinActivity.class);
                i.putExtra("title",markertitle);
                startActivity(i);
                return false;
            }
        });
        */

    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            //PermissionRequest.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    //Manifest.permission.ACCESS_FINE_LOCATION, true);

        }
    }

    private void savePin(String pinName, String pinCaption, ParseUser currentUser, ParseGeoPoint pinLocation) {
        Pin pin = new Pin();
        //ParseObject.create("Pin");
        pin.setPinName(pinName);
        pin.setCreator(currentUser);
        pin.setPinCaption(pinCaption);
        pin.setPinGeoPoint(pinLocation);
        pin.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    return;
                }
                Log.i(TAG, "Pin save was successful!!!");
                Toast.makeText(getContext(), "Pin Save Successful!", Toast.LENGTH_SHORT).show();
                //editTitle.setText("");
                //editDescription.setText("");
                pinGeoPoint = null;
            }
        });
    }
    public void createObject(String pinName, String pinCaption, ParseUser currentUser, ParseGeoPoint pinLocation) {
        ParseObject entity = new ParseObject("Pin");

        entity.put("pinName", pinName);
        entity.put("pinCaption", pinCaption);
        entity.put("creator", userID);
        entity.put("pinLocation", pinLocation);

        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
        entity.saveInBackground(e -> {
            if (e == null) {
                //Save was done
            } else {
                //Something went wrong
                //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Pin save was successful!!!");
            }
        });

    }

}