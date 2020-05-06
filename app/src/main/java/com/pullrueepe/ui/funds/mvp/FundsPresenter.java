package com.pullrueepe.ui.funds.mvp;

import android.util.Patterns;

import com.pullrueepe.data.rest.ApiClient;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.login.LoginResponse;


public class FundsPresenter implements FundsContractor.Presenter {

    private FundsContractor.View mView;
    private FundsModel model;
    private AppRepository appRepository;
    private ApiInterface apiInterface;

    public FundsPresenter(FundsContractor.View view, AppRepository appRepository1) {
        mView = view;
        model = new FundsModel(this);
        this.appRepository = appRepository1;
        apiInterface = ApiClient.getApiInterface();
    }


    @Override
    public void onCreateView(String clientId, String sessionId) {
        mView.showLoadingView();
        model.requestFunds(apiInterface,clientId,sessionId);
    }

    @Override
    public void submitButtonClicked(String clientId,int transactionType,String amount,String date) {
        mView.showLoadingView();
        model.submitAmount(apiInterface, clientId,transactionType,amount,date);
    }

    @Override
    public void Success(Funds funds) {
        mView.hideLoadingView();
        mView.onCreateView(funds);

    }

    @Override
    public void Error(String error) {
        mView.hideLoadingView();
        mView.showError(error);
    }

    @Override
    public void Error(int error) {
        mView.hideLoadingView();
        mView.showError(error);
    }

    @Override
    public void createFundsRefresh() {
        mView.hideLoadingView();
        mView.createFundsSuccess();
    }

    @Override
    public void close() {

    }
}
