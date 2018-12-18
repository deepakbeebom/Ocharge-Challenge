package com.example.deepak.ochargetask.application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import com.example.deepak.ochargetask.roomDatabase.UserRoomDatabase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    private OchargeApplication ochargeApplication;

    AppModule(OchargeApplication ochargeApplication) {
        this.ochargeApplication = ochargeApplication;
    }

    @Provides
    @Singleton
    OchargeApplication provideOchargeApplication(){
        return ochargeApplication;
    }

    @Provides
    @Singleton
    UserRoomDatabase provideRoomDatabase(OchargeApplication ochargeApplication){
        UserRoomDatabase database = Room.databaseBuilder(ochargeApplication,
                UserRoomDatabase.class, "user_database").build();
        return database;
    }

    @Provides
    ViewModelProvider.Factory getFactory(){
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//                return (T) new AndroidViewModel(ochargeApplication);
                return (T) new UserViewModel(ochargeApplication);
            }
        };
    }

}
