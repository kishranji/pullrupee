package com.pullrueepe.ui.login.mvp;

import com.pullrueepe.BaseView;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.model.symbol.GetSymbolResponse;

public interface LoginContractor {

    interface View extends BaseView {

        void showEmailEmptyError();

        void showNotValidEmailError();

        void showPasswordEmptyError();

        void showLoginResponse(LoginResponse loginResponse);

        void getSymbolList(GetSymbolResponse getSymbolResponse);
    }

    interface Presenter {

        void loginButtonClicked(String email, String password, String devicemodel,
                                String osversion, String imeinumber, String mobilebrand);

        void loginSuccess(LoginResponse loginResponse);

        void loginError(String error);

        void loginError(int error);

        void close();

        void fetchSymbolList(GetSymbolResponse getSymbolResponse);
    }

    interface Model {

        void requestLogin(ApiInterface apiInterface, String email, String password, String devicemodel,
                          String osversion, String imeinumber, String mobilebrand);

        void close();

        void getSymbols(ApiInterface apiInterface);
    }
}
