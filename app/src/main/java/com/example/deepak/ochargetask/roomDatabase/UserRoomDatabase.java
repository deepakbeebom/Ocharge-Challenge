package com.example.deepak.ochargetask.roomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.deepak.ochargetask.application.OchargeApplication;
import com.example.deepak.ochargetask.dao.UserDao;
import com.example.deepak.ochargetask.entity.UserEntity;
import io.reactivex.annotations.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    private static volatile UserRoomDatabase INSTANCE;

    public abstract UserDao userDao();

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            populateData(INSTANCE);
        }
    };

    private static void populateData(UserRoomDatabase db) {

        final UserDao mDao;
        mDao = db.userDao();
        LiveData<List<UserEntity>> list = mDao.getAllUsers();
        List<UserEntity> userList = list.getValue();
        if (userList == null || userList.size() == 0) {
            Log.e("user list null", "true");
        } else {
            Log.e("user list null", "false");
        }
        StringRequest request = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/posts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDao.deleteAll();
                                    JSONArray array = null;
                                    try {
                                        array = new JSONArray(response);
                                        Log.e("array", array.toString());
                                        int length = array.length();
                                        Log.e("array length", length + "");
                                        for (int i = 0; i < length; i++) {
                                            JSONObject jsonObject = array.getJSONObject(i);
                                            UserEntity userEntity = new UserEntity();
                                            userEntity.setUserId(jsonObject.getInt("userId"));
                                            userEntity.setId(jsonObject.getInt("id"));
                                            userEntity.setTitle(jsonObject.getString("title"));
                                            userEntity.setBody(jsonObject.getString("body"));
                                            mDao.insertUser(userEntity);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
//                                }
                            });

//                            UserEntity user = new UserEntity();
//                            mDao.insertUser(user);

                        } catch (Exception e) {
                            Log.e("exception", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("on error", error.toString());
            }
        });
        OchargeApplication.getInstance().addToRequestQueue(request);
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        UserDao mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            populateData(INSTANCE);
            UserEntity userEntity = new UserEntity();
            userEntity.setId(1);
            userEntity.setUserId(1);
            userEntity.setBody("body");
            userEntity.setTitle("title");
            mDao.insertUser(userEntity);
//            user = new Word("World");
//            mDao.insert(user);
            return null;
        }
    }

}
