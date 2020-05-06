package com.pullrueepe.ui.monthlyTarget.mvp;


import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.pullrueepe.R;
import com.pullrueepe.data.rest.ApiInterface;


import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.CreateMonthlyRequest;
import com.pullrueepe.model.monthlyTarget.CreateMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyRequest;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetRequest;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetResult;
import com.pullrueepe.model.monthlyTarget.ParticularMonthRequest;
import com.pullrueepe.model.monthlyTarget.ParticularMonthTarget;
import com.pullrueepe.util.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MonthlyTargetModel implements MonthlyTargetContractor.Model {


    private MonthlyTargetPresenter mPresenter;
    private CompositeDisposable disposable = new CompositeDisposable();

    MonthlyTargetModel(MonthlyTargetPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void requestMonthlyTarget(ApiInterface apiInterface, String clientId, int tradeYear, int tradeMonth) {
        MonthlyTargetRequest monthlyTargetRequest = new MonthlyTargetRequest();
        monthlyTargetRequest.setClientid(clientId);
        monthlyTargetRequest.setTrademonth(String.valueOf(tradeMonth));
        monthlyTargetRequest.setTradeyear(String.valueOf(tradeYear));
        disposable.add(apiInterface
                .monthlyTarget(clientId, monthlyTargetRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MonthlyTargetResult>() {
                    @Override
                    public void onSuccess(MonthlyTargetResult monthlyTargetResult) {
                        mPresenter.Success(monthlyTargetResult);
                        Log.d("monthlysucess",""+monthlyTargetResult);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.Error(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.Error(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.Error(R.string.network_error);
                        } else {
                            mPresenter.Error(e.getMessage());
                        }

                    }
                }));

    }

    @Override
    public void submitAmount(ApiInterface apiInterface, String clientId, int tradeYear, int tradeMonth, String amount) {
        CreateMonthlyRequest createMonthlyRequest = new CreateMonthlyRequest();
        createMonthlyRequest.setClientid(clientId);
        createMonthlyRequest.setTradeyear(tradeYear);
        createMonthlyRequest.setTargetamount(amount);
        createMonthlyRequest.setTrademonth(tradeMonth);
        disposable.add(apiInterface.createMonthlyTarget(clientId, createMonthlyRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CreateMonthlyTarget>() {
                    @Override
                    public void onSuccess(CreateMonthlyTarget createMonthlyTarget) {

                        mPresenter.createMonthlyRefresh();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.Error(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.Error(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.Error(R.string.network_error);
                        } else {
                            mPresenter.Error(e.getMessage());
                        }

                    }
                }));
    }

    @Override
    public void setTargetAmount(ApiInterface apiInterface, String clientId, int tradeYear, int tardeMonth,String actionButton) {
        ParticularMonthRequest particularMonthRequest = new ParticularMonthRequest();
        particularMonthRequest.setClientid(clientId);
        particularMonthRequest.setYear(tradeYear);
        particularMonthRequest.setMonth(tardeMonth);
        particularMonthRequest.setActionbutton(actionButton);
        disposable.add(apiInterface.getParticularMonthTarget(clientId, particularMonthRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ParticularMonthTarget>() {
                    @Override
                    public void onSuccess(ParticularMonthTarget particularMonthTarget) {

                        mPresenter.getTagrgetAamount(particularMonthTarget);
                        Crashlytics.setString("monthly target", particularMonthTarget.getResponsemessage());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.Error(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.Error(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.Error(R.string.network_error);
                        } else {
                            mPresenter.Error(e.getMessage());
                        }

                    }
                }));
    }

    @Override
    public void onRetriveHistory(ApiInterface apiInterface,String clientId, int tradeYear) {
        GetAllMonthlyRequest getAllMonthlyRequest = new GetAllMonthlyRequest();
        getAllMonthlyRequest.setClientid(clientId);
        getAllMonthlyRequest.setTradeyear(tradeYear);

        disposable.add(apiInterface.getAllMonthTarget(clientId, getAllMonthlyRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<GetAllMonthlyTarget>() {
                    @Override
                    public void onSuccess(GetAllMonthlyTarget getAllMonthlyTarget) {
                     //   List<AllMonthlyTargets> getAllMonthlyTargetList=getAllMonthlyTarget.getPullrupeedata();
                        Log.d("MonthlyTarget",""+getAllMonthlyTarget.getPullrupeedata());

                        mPresenter.monthlyTargetHistory(getAllMonthlyTarget);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mPresenter.Error(NetworkUtils.getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            mPresenter.Error(R.string.time_out_error);
                        } else if (e instanceof IOException) {
                            mPresenter.Error(R.string.network_error);
                        } else {
                            mPresenter.Error(e.getMessage());
                        }

                    }
                }));
    }

    @Override
    public void close() {
        disposable.dispose();

    }
}
