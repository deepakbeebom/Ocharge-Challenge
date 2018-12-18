package com.example.deepak.ochargetask.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.deepak.ochargetask.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity userEntity);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table")
    LiveData<List<UserEntity>> getAllUsers();
}
