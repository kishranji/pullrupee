package com.pullrueepe.ui.login.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.TelephonyManager;
import android.util.Log;


import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pullrueepe.R;
import com.pullrueepe.base.BaseActivity;
import com.pullrueepe.database.DatabaseHelper;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.model.symbol.GetSymbolResponse;
import com.pullrueepe.ui.funds.activity.MainActivity;
import com.pullrueepe.ui.login.mvp.LoginContractor;
import com.pullrueepe.ui.login.mvp.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;


public class Login extends BaseActivity implements LoginContractor.View {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private static final String FUNDS = "funds";
    private TelephonyManager mTelephonyManager;

    @BindView(R.id.et_email)
    AppCompatEditText etEmailAddress;

    @BindView(R.id.et_password)
    AppCompatEditText etPassword;

    LoginPresenter loginPresenter;

    String session_id, client_id;
    String userName;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTelephonyManager = (TelephonyManager)
                getSystemService(this.TELEPHONY_SERVICE);

        if (appRepository.isLoggedIn()) {
            startActivity(MainActivity.newInstance(Login.this, session_id,
                    client_id, userName, "Funds"));
            finish();
        } else {
            loginPresenter = new LoginPresenter(this, appRepository);
            loginPresenter.onVeiwCreate();
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }


    public String getUniqueIMEIId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE);
            } else {
                String imei = null;
                /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    imei = mTelephonyManager.getImei();
                }*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = mTelephonyManager.getImei();
                } else {
                    imei = mTelephonyManager.getDeviceId();
                }
                Log.e("imei", "=" + imei);
                if (imei != null && !imei.isEmpty()) {
                    return imei;
                } else {
                    return Build.SERIAL;
                }


            }

        }
        return "not_found";
    }

    @OnClick(R.id.btn_login)
    public void login() {
        if (isNetworkConnected()) {
            String devicemodel = getDeviceName();
            String androidOS = Build.VERSION.RELEASE;
            String imeinumber = getUniqueIMEIId();
            String mobileBrand = Build.BRAND;
            //  startActivity(new Intent(Login.this, MainActivity.class));

            loginPresenter.loginButtonClicked(etEmailAddress.getText().toString().trim(),
                    etPassword.getText().toString().trim(), devicemodel,
                    androidOS, imeinumber, mobileBrand);
        } else {
            showToast(R.string.no_internet_connection);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        loginPresenter.close();
    }


    @Override
    public void showEmailEmptyError() {
        etEmailAddress.setError(getString(R.string.warning_empty_email));

    }

    @Override
    public void showNotValidEmailError() {
        etEmailAddress.setError(getString(R.string.warning_invalid_email));
        etEmailAddress.setFocusable(true);
        etEmailAddress.requestFocus();
    }

    @Override
    public void showPasswordEmptyError() {
        etPassword.setError(getString(R.string.warning_empty_password));
        etPassword.setFocusable(true);
        etPassword.requestFocus();
    }

    @Override
    public void showLoginResponse(LoginResponse loginResponse) {
        hideKeyboard();
        //   startActivity(new Intent(Login.this, MainActivity.class));
        if (loginResponse.getResult().equals("fail")) {
            showToast(loginResponse.getResponsemessage());
            logLogin("Failed");
        } else {
            logLogin("Success");
            // startActivity(new Intent(Login.this, FundsActivity.class));
            session_id = appRepository.getSessionId();
            client_id = appRepository.getUserId();
            userName = loginResponse.getPullrupeedata().getName();
            Log.d("session_id", session_id);
            Log.d("client_id", client_id);
            startActivity(MainActivity.newInstance(Login.this, session_id,
                    client_id, userName, "Funds"));
            finish();
        }
    }

    @Override
    public void getSymbolList(GetSymbolResponse getSymbolResponse) {
        DatabaseHelper db = new DatabaseHelper(Login.this);
        db.clearTable();
        db.insertSymbol(getSymbolResponse.getPullrupeedata());
    }

    @Override
    public void showLoadingView() {
        showProgress();
    }

    @Override
    public void hideLoadingView() {
        hideProgress();
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void showError(int message) {
        showToast(message);
    }


    @Override
    public void onBackPressed() {

// dont call **super**, if u want disable back button in current screen.
    }

    private void logLogin(String isSuccess) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Event.LOGIN, etEmailAddress.getText().toString());
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();
        }
    }
}