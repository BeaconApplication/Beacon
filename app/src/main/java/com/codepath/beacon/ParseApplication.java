package com.codepath.beacon;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YWqP6yrwN9rR8rOQX8nb0mEn2oRKZckQmlJ2BolM")
                .clientKey("FcEvDPZzAU0aNYMWROygiEYJ5fy5an9p2yRD45E2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
