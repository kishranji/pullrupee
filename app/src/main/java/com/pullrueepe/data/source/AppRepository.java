package com.pullrueepe.data.source;

public class AppRepository implements AppDataSource {


    private AppDataSource mSharedPrefercenseDataSource;

    public AppRepository(AppDataSource sharedPreferences) {
        this.mSharedPrefercenseDataSource = sharedPreferences;
    }

    @Override
    public void saveIsLoggedIn(boolean isLoggedIn) {
        mSharedPrefercenseDataSource.saveIsLoggedIn(isLoggedIn);
    }

    @Override
    public boolean isLoggedIn() {
        return mSharedPrefercenseDataSource.isLoggedIn();
    }

    @Override
    public void setUserId(String clientid) {
        mSharedPrefercenseDataSource.setUserId(clientid);
    }

    @Override
    public String getUserId() {
        return mSharedPrefercenseDataSource.getUserId();
    }

    @Override
    public void setOAuthKey(String oAuthKey) {
        mSharedPrefercenseDataSource.setOAuthKey(oAuthKey);
    }

    @Override
    public String getOAuthKey() {
        return mSharedPrefercenseDataSource.getOAuthKey();
    }

    @Override
    public void setSessionId(String sessionId) {
        mSharedPrefercenseDataSource.setSessionId(sessionId);
    }

    @Override
    public String getSessionId() {
        return mSharedPrefercenseDataSource.getSessionId();
    }
}
