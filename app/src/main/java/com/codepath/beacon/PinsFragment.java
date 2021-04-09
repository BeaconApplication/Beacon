package com.codepath.beacon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.beacon.PinsAdapter;
import com.codepath.beacon.Pin;
import com.codepath.beacon.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinsFragment extends Fragment {

    public static final String TAG = "PinsFragment";

    private RecyclerView rvPins;
    private PinsAdapter adapter;
    private List<Pin> allPins;

    public PinsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pins, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPins = view.findViewById(R.id.rvPins);
        allPins = new ArrayList<>();
        adapter = new PinsAdapter(getContext(), allPins);
        rvPins.setAdapter(adapter);
        rvPins.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPins();
    }

    private void queryPins() {
        ParseQuery<Pin> query = ParseQuery.getQuery(Pin.class);
        // Filters the pins to only current users pins, assuming this is a My Pins screen
        query.whereEqualTo(Pin.KEY_CREATOR, ParseUser.getCurrentUser());
        // TODO: add some sort of order with query.addDescendingOrder, maybe alphabetical?
        query.findInBackground(new FindCallback<Pin>() {
            @Override
            public void done(List<Pin> pins, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "issue with getting pins", e);
                    return;
                }

                allPins.addAll(pins);
                adapter.notifyDataSetChanged();
            }
        });

    }
}