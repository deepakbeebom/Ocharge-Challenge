package com.example.deepak.ochargetask.application;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.example.deepak.ochargetask.entity.UserEntity;
import com.example.deepak.ochargetask.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<UserEntity>> mAllUsers;

    @Inject
    UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<UserEntity>> getAllUsers() { return mAllUsers; }


    public void insert(UserEntity userEntity) { mRepository.insert(userEntity); }
}
