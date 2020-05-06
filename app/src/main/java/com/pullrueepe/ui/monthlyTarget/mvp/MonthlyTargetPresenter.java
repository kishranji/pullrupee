package com.pullrueepe.ui.monthlyTarget.mvp;

import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.pullrueepe.data.rest.ApiClient;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetResult;
import com.pullrueepe.model.monthlyTarget.ParticularMonthTarget;

import java.util.List;


public class MonthlyTargetPresenter implements MonthlyTargetContractor.Presenter {

    private MonthlyTargetContractor.View mView;
    private MonthlyTargetModel model;
    private AppRepository appRepository;
    private ApiInterface apiInterface;

    public MonthlyTargetPresenter(MonthlyTargetContractor.View view, AppRepository appRepository1) {
        mView = view;
        model = new MonthlyTargetModel(this);
        this.appRepository = appRepository1;
        apiInterface = ApiClient.getApiInterface();
    }


    @Override
    public void onCreateView(String clientId, int tradeYear, int tradeMonth) {
      //  mView.showLoadingView();
        Log.d("onCreate", "onCreateView");
        model.requestMonthlyTarget(apiInterface, clientId, tradeYear, tradeMonth);
    }

    @Override
    public void submitButtonClicked(String clientId, int tradeYear, int tradeMonth, String amount) {
      //  mView.showLoadingView();
        model.submitAmount(apiInterface, clientId, tradeYear, tradeMonth, amount);


    }

    @Override
    public void Success(MonthlyTargetResult monthlyTarget) {
        Log.d("show Success", "show Success");
        mView.onCreateedView(monthlyTarget);

    }

    @Override
    public void Error(String error) {
        mView.showError(error);
        Crashlytics.log(error);
    }

    @Override
    public void Error(int error) {
        mView.showError(""+error);
        Crashlytics.log(""+error);
    }

    @Override
    public void createMonthlyRefresh() {
      //  mView.hideLoadingView();
        mView.createMonthlySuccess();
    }

    @Override
    public void close() {

    }

    @Override
    public void onSetTargetAmount(String clientId, int tradeYear, int tradeMonth,String actionButton) {
     //   mView.showLoadingView();
        model.setTargetAmount(apiInterface, clientId, tradeYear, tradeMonth,actionButton);
    }

    @Override
    public void getTagrgetAamount(ParticularMonthTarget particularMonthTarget) {
        //mView.hideLoadingView();
        mView.showTargetAmount(particularMonthTarget);

    }

    @Override
    public void onretriveHistory(String clientId, int tradeYear) {
      //  mView.showLoadingView();
        model.onRetriveHistory(apiInterface,clientId, tradeYear);
    }

    @Override
    public void monthlyTargetHistory(GetAllMonthlyTarget monthlyTarget) {
        //mView.hideLoadingView();
        Log.d("list",""+monthlyTarget);
        mView.monthlyTargetHistory(monthlyTarget);
    }
}
