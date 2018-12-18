package com.example.deepak.ochargetask.application;

import android.app.Activity;
import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

public class OchargeApplication extends Application implements HasActivityInjector {

    private static OchargeApplication instance;
    private RequestQueue requestQueue;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;


    public OchargeApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);

        if (instance == null)
            instance = this;
    }

    public synchronized static OchargeApplication getInstance() {
        return instance;
    }

    public void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }

    private synchronized RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
