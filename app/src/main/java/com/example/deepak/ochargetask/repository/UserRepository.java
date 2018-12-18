package com.example.deepak.ochargetask.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.example.deepak.ochargetask.dao.UserDao;
import com.example.deepak.ochargetask.entity.UserEntity;
import com.example.deepak.ochargetask.roomDatabase.UserRoomDatabase;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserEntity>> mAllUsers;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        mAllUsers = userDao.getAllUsers();
    }

    public LiveData<List<UserEntity>> getAllUsers() {

        return mAllUsers;
    }

    public void insert (UserEntity userEntity) {
        new insertAsyncTask(userDao).execute(userEntity);
    }

    private static class insertAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserEntity... params) {
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }

}
