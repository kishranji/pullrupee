package com.pullrueepe.ui.funds.mvp;


import com.crashlytics.android.Crashlytics;
import com.pullrueepe.R;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.funds.CreateFund;
import com.pullrueepe.model.funds.CreateFundRequest;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.funds.FundsRequest;
import com.pullrueepe.model.login.LoginRequest;
import com.pullrueepe.model.login.LoginResponse;
import com.pullrueepe.util.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class FundsModel implements FundsContractor.Model {


    private FundsPresenter mPresenter;
    private CompositeDisposable disposable = new CompositeDisposable();

    FundsModel(FundsPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void requestFunds(ApiInterface apiInterface, String clientId, String sessionId) {
        FundsRequest fundsRequest = new FundsRequest();
        fundsRequest.setClientid(clientId);
        fundsRequest.setSessionid(sessionId);
        disposable.add(apiInterface
                .funds(clientId,fundsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Funds>() {
                    @Override
                    public void onSuccess(Funds funds) {
                        Crashlytics.setString("Funds_response", funds.getResponsemessage());
                        mPresenter.Success(funds);
                    }

                    @Override
                    public void onError(Throwable e) {
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
    public void submitAmount(ApiInterface apiInterface,String clientId, int transactionType, String amount, String date) {
        CreateFundRequest createFundRequest = new CreateFundRequest();
        createFundRequest.setClientid(clientId);
        createFundRequest.setTransactionamount(amount);
        createFundRequest.setTransactiontype(transactionType);
        createFundRequest.setTransactiondate(date);
        disposable.add(apiInterface
                .createFunds(clientId,createFundRequest)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CreateFund>() {
                    @Override
                    public void onSuccess(CreateFund createFund) {
                        mPresenter.createFundsRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
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
