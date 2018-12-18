package com.example.deepak.ochargetask.application;

import com.example.deepak.ochargetask.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
