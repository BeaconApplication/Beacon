package com.codepath.beacon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePinActivity extends AppCompatActivity {
    public static final String TAG = "CreatePinActivity";

    private EditText editTitle;
    private EditText editDescription;
    private TextView titleText;
    private TextView createPinText;
    private CheckBox publicPrivate;
    private CheckBox groupCheck;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        titleText = findViewById(R.id.titleText);
        createPinText = findViewById(R.id.createPinText);
        publicPrivate = findViewById(R.id.publicPrivate);
        groupCheck = findViewById(R.id.groupCheck);
        shareButton = findViewById(R.id.shareButton);

    }
}