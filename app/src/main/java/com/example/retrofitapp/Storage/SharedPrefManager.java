package com.example.retrofitapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.retrofitapp.model.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME="my_shred_preff";

    private static SharedPrefManager mInstance;

    private Context mCtx;

  private SharedPrefManager(Context mCtx) {this.mCtx=mCtx;}


public boolean isLogedIn(){

    SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1) !=-1;

  }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("school", null)
        );
    }

  public void saveUser(User user){

        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt("id",user.getId());
        editor.putString("email",user.getEmail());
        editor.putString("name",user.getName());
        editor.putString("school",user.getSchool());

        editor.apply();
  }

public static synchronized SharedPrefManager getInstance(Context mCtx){

        if (mInstance==null){

            mInstance=new SharedPrefManager(mCtx);

        }
    return mInstance;
}

public  void clear(){

        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

}
}
