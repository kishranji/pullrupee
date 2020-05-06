package com.pullrueepe.data.source;

public interface AppDataSource {

    void saveIsLoggedIn(boolean isLoggedIn);

    boolean isLoggedIn();

    void setUserId(String userId);

    String getUserId();

    void setOAuthKey(String oAuthKey);

    String getOAuthKey();
    void setSessionId(String sessionId);

    String getSessionId();

}
