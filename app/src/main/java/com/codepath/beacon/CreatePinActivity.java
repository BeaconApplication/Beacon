package com.codepath.beacon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class CreatePinActivity extends Fragment {
    public static final String TAG = "CreatePinActivity";

    private EditText editTitle;
    private EditText editDescription;
    private TextView titleText;
    private TextView createPinText;
    private CheckBox publicPrivate;
    private CheckBox groupCheck;
    private Button shareButton;

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

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinName = editTitle.getText().toString();
                String pinCaption = editDescription.getText().toString();

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
}