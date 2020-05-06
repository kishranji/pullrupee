package com.pullrueepe.ui.IntradayCalculator.mvp;

import com.pullrueepe.data.rest.ApiClient;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.model.symbol.SymbolData;
import com.pullrueepe.ui.IntradayCalculator.mvp.IntradayContractor;

import java.util.ArrayList;

public class IntradayPresenter implements IntradayContractor.Presenter {

    IntradayContractor.View mView;
    IntradayContractor.Model mModel;
    private AppRepository appRepository;
    private ApiInterface apiInterface;

    public IntradayPresenter(IntradayContractor.View mView, AppRepository appRepository1) {
        this.mView = mView;
        this.mModel = new IntradayModel(this);
        this.appRepository = appRepository1;
        apiInterface = ApiClient.getApiInterface();
    }

    @Override
    public void onViewCreated() {
        mModel.getSymbolDetailsFromDB();
    }

    @Override
    public void onSymbolFetched(ArrayList<SymbolData> symbolList) {
        mView.populateSymbol(symbolList);
    }
}
