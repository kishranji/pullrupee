package com.pullrueepe.ui.login.mvp;


import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.pullrueepe.R;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.login.LoginRequest;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.model.symbol.GetSymbolResponse;
import com.pullrueepe.util.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginModel implements LoginContractor.Model {


    private LoginPresenter mPresenter;
    private CompositeDisposable disposable = new CompositeDisposable();

    LoginModel(LoginPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void requestLogin(ApiInterface apiInterface, String emailid, String password, String devicemodel,
                             String osversion, String imeinumber, String mobilebrand) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setEmailid(emailid);
        loginRequest.setDevicemodel(devicemodel);
        loginRequest.setImeinumber(imeinumber);
        loginRequest.setOsversion(osversion);
        loginRequest.setMobilebrand(mobilebrand);
        disposable.add(apiInterface
                .signIn(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        mPresenter.loginSuccess(loginResponse);
                        Crashlytics.setString("Login_response", loginResponse.getResponsemessage());
                        Log.e("response", "" + loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.loginError(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.loginError(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.loginError(R.string.network_error);
                        } else {
                            mPresenter.loginError(e.getMessage());
                        }

                    }
                }));
    }

    @Override
    public void close() {
        disposable.dispose();
    }

    @Override
    public void getSymbols(ApiInterface apiInterface) {
        disposable.add(apiInterface
                .getSymbol()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<GetSymbolResponse>() {
                    @Override
                    public void onSuccess(GetSymbolResponse getSymbolResponse) {
                          mPresenter.fetchSymbolList(getSymbolResponse);
                        Log.e("response", "" + getSymbolResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.loginError(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.loginError(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.loginError(R.string.network_error);
                        } else {
                            mPresenter.loginError(e.getMessage());
                        }

                    }
                }));
    }
}
