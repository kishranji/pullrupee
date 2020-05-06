package com.pullrueepe.ui.login.mvp;

import android.util.Patterns;


import com.crashlytics.android.Crashlytics;
import com.pullrueepe.data.rest.ApiClient;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.model.symbol.GetSymbolResponse;


public class LoginPresenter implements LoginContractor.Presenter {

    private LoginContractor.View mView;
    private LoginModel model;
    private AppRepository appRepository;
    private ApiInterface apiInterface;

    public LoginPresenter(LoginContractor.View view, AppRepository appRepository1) {
        mView = view;
        model = new LoginModel(this);
        this.appRepository = appRepository1;
        apiInterface = ApiClient.getApiInterface();
    }


    @Override
    public void loginButtonClicked(String emailid, String password, String devicemodel,
                                   String osversion, String imeinumber, String mobilebrand) {
        if (emailid.length() == 0) {
            mView.showEmailEmptyError();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            mView.showNotValidEmailError();
        } else if (password.length() == 0) {
            mView.showPasswordEmptyError();
        } else {
            mView.showLoadingView();
            model.requestLogin(apiInterface, emailid, password, devicemodel, osversion, imeinumber, mobilebrand);
        }
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        mView.hideLoadingView();
        if (loginResponse.getResult().equals("fail")) {
            mView.showLoginResponse(loginResponse);
        } else {
            appRepository.saveIsLoggedIn(true);
            appRepository.setSessionId(loginResponse.getPullrupeedata().getSessionid());
            appRepository.setUserId(loginResponse.getPullrupeedata().getClientid());
            mView.showLoginResponse(loginResponse);
        }
    }

    @Override
    public void loginError(String error) {
        Crashlytics.log(error);
        mView.hideLoadingView();
        mView.showError(error);
    }

    @Override
    public void loginError(int error) {
        mView.hideLoadingView();
        mView.showError(error);
    }

    @Override
    public void close() {
        model.close();
    }

    @Override
    public void fetchSymbolList(GetSymbolResponse getSymbolResponse) {
        mView.getSymbolList(getSymbolResponse);
    }

    public void onVeiwCreate() {
        //mView.showLoadingView();
        model.getSymbols(apiInterface);
    }
}
