package com.saeedbaharikhoob.testproject.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Account {


    static Account instant;
    String token;
    int userId;
    boolean login;


    Context context;


    public Account() {

    }


    /**
     * storeToken
     * @param token
     */
    public void storeToken(String token) {
        this.token = token;
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }
    /**
     * getToken
     * @return
     */
    public String getToken() {

        if (token != null && token.length() > 1)
            return token;

        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        token = app_preferences.getString("token", null);
        return token;

    }
    /**
     * storeUserId
     * @param userId
     */
    public void storeUserId(int userId) {
        this.userId = userId;
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putInt("userId", userId);
        editor.commit();
    }
    /**
     * getToken
     * @return
     */
    public int getUserId() {

        if (userId > 0)
            return userId;

        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        userId = app_preferences.getInt("userId", 0);
        return userId;

    }


    /**
     * store Status Login
     * @param status
     */
    public void storeStatusLogin(Boolean status) {
        this.login = status;
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putBoolean("login", login);
        editor.commit();
    }

    /**
     * get Status Login
     * @return
     */
    public Boolean getStatusLogin() {


        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        login = app_preferences.getBoolean("login", false);
        return login;


    }

    /**
     * check user already have token
     * @return
     */
    public boolean alreadyHaveToken() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        String token;
        token = app_preferences.getString("token", null);

        if (token == null) return false;

        return token.length() >= 1;

    }

    /**
     * clear Token
     */
    public void clearToken() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("token", "");
        editor.commit();
    }

    /**
     * get Instant Account
     * @param context
     * @return
     */
    public static Account getInstant(Context context) {

        if (instant == null && context != null) {
            instant = new Account();
            instant.context = context;
        }

        return instant;

    }
}