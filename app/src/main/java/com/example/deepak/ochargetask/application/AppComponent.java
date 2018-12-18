package com.example.deepak.ochargetask.application;

import android.arch.lifecycle.ViewModelProvider;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, BuildersModule.class, AppModule.class})
public interface AppComponent {

    void inject(OchargeApplication ochargeApplication);

//    @Provides
//    ViewModelProvider.Factory getFactory(OchargeApplication ochargeApplication);
}
