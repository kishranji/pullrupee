package com.pullrueepe.ui.holdings.mvp;

import com.crashlytics.android.Crashlytics;
import com.pullrueepe.data.rest.ApiClient;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.model.holdings.HoldingResult;


public class HoldingsPresenter implements HoldingsContractor.Presenter {

    private HoldingsContractor.View mView;
    private HoldingsModel model;
    private AppRepository appRepository;
    private ApiInterface apiInterface;

    public HoldingsPresenter(HoldingsContractor.View view, AppRepository appRepository1) {
        mView = view;
        model = new HoldingsModel(this);
        this.appRepository = appRepository1;
        apiInterface = ApiClient.getApiInterface();
    }


    @Override
    public void onCreateView(String clientId) {
        mView.showLoadingView();
        model.GetHoldings(apiInterface, clientId);
    }

    @Override
    public void Success(HoldingResult holdingResult) {
        mView.hideLoadingView();
        mView.onCreateView(holdingResult);

    }

    @Override
    public void Error(String error) {
        Crashlytics.log(error);
        mView.hideLoadingView();
        mView.showError(error);
    }

    @Override
    public void Error(int error) {
        Crashlytics.log(""+error);
        mView.hideLoadingView();
        mView.showError(error);
    }


    @Override
    public void close() {

    }
}
