package com.pullrueepe.data.source.sharedpreference;

import android.content.Context;

public interface PreferenceKeys {

    String PREF_NAME = "PEOPLE_PREFERENCES";
    int MODE = Context.MODE_PRIVATE;

    String IS_LOGGED_IN = "IsLoggedIn";
    String USER_ID = "clientId";
    String OAUTH_KEY = "OAuthKey";
    String SESSION_ID = "SessionId";
}
