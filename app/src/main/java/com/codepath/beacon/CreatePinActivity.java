package com.codepath.beacon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseGeoPoint;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class CreatePinActivity extends Fragment {
    public static final String TAG = "CreatePinActivity";

    private EditText editTitle;
    private EditText editDescription;
    private String creator;

    private TextView titleText;
    private TextView createPinText;
    public LatLng pinLocation;
    //private CheckBox publicPrivate;
    //private CheckBox groupCheck;
    private Button shareButton;
    private Button logoutButton;
    private static ParseGeoPoint pinGeoPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.activity_create_pin, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createPinText = view.findViewById(R.id.createPinText);
        editTitle = view.findViewById(R.id.editTitle);
        editDescription = view.findViewById(R.id.editDescription);
        shareButton = view.findViewById(R.id.shareButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinName = editTitle.getText().toString();
                if (pinName.isEmpty()){
                    Toast.makeText(getContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pinCaption = editDescription.getText().toString();
                if (pinGeoPoint == null){
                    Toast.makeText(getContext(), "Error Getting Pin Location - Please Select a Location on the Map", Toast.LENGTH_SHORT).show();
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                //savePin(pinName, pinCaption, currentUser, pinGeoPoint);

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //editTitle = findViewById(R.id.editTitle);
        //editDescription = findViewById(R.id.editDescription);
        //titleText = findViewById(R.id.titleText);
        //createPinText = findViewById(R.id.createPinText);
        //publicPrivate = findViewById(R.id.publicPrivate);
        //groupCheck = findViewById(R.id.groupCheck);
        //shareButton = findViewById(R.id.shareButton);

    }
    public void updatePinLocation(LatLng latlng){
        pinLocation = latlng;
        pinGeoPoint = new ParseGeoPoint(latlng.latitude, latlng.longitude);
    }
    public ParseGeoPoint getPinGeoPoint(){
        return pinGeoPoint;
    }
    private void savePin(String pinName, String pinCaption, ParseUser currentUser, ParseGeoPoint pinLocation) {
        Pin pin = new Pin();
        pin.setPinName(pinName);
        pin.setPinCaption(pinCaption);
        pin.setPinGeoPoint(pinGeoPoint);
        pin.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    return;
                }
                Log.i(TAG,"Pin save was successful!!!");
                Toast.makeText(getContext(), "Pin Save Successful!", Toast.LENGTH_SHORT).show();
                editTitle.setText("");
                editDescription.setText("");
                pinGeoPoint = null;
            }
        });
    }
}
