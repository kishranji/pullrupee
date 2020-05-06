package com.pullrueepe.data.source.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.pullrueepe.data.source.AppDataSource;


public class AppPreferenceDataSource implements AppDataSource, PreferenceKeys {

    private SharedPreferences sharedPreferences;

    public AppPreferenceDataSource(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE);
    }

    @Override
    public void saveIsLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply();

    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    @Override
    public void setUserId(String userId) {
        sharedPreferences.edit().putString(USER_ID, userId).apply();
    }

    @Override
    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "0");
    }

    @Override
    public void setOAuthKey(String oAuthKey) {
        sharedPreferences.edit().putString(OAUTH_KEY, oAuthKey).apply();
    }

    @Override
    public String getOAuthKey() {
        return sharedPreferences.getString(OAUTH_KEY, "");
    }

    @Override
    public void setSessionId(String sessionId) {
        sharedPreferences.edit().putString(SESSION_ID, sessionId).apply();
    }

    @Override
    public String getSessionId() {
        return sharedPreferences.getString(SESSION_ID, "");
    }
}
