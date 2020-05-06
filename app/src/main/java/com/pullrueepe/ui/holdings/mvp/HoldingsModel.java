package com.pullrueepe.ui.holdings.mvp;


import com.crashlytics.android.Crashlytics;
import com.pullrueepe.R;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.holdings.HoldingRequest;
import com.pullrueepe.model.holdings.HoldingResult;
import com.pullrueepe.util.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class HoldingsModel implements HoldingsContractor.Model {


    private HoldingsPresenter mPresenter;
    private CompositeDisposable disposable = new CompositeDisposable();

    HoldingsModel(HoldingsPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void GetHoldings(ApiInterface apiInterface, String clientId) {

        HoldingRequest request = new HoldingRequest();
        request.setClientid(clientId);

        disposable.add(apiInterface

                .getHolding(clientId, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<HoldingResult>() {
                    @Override
                    public void onSuccess(HoldingResult holdingResult) {
                        mPresenter.Success(holdingResult);
                        Crashlytics.setString("Holding_response", holdingResult.getResponsemessage());
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
