package com.pullrueepe.ui.IntradayCalculator.mvp;

import com.pullrueepe.BaseApplication;
import com.pullrueepe.database.DatabaseHelper;

public class IntradayModel implements IntradayContractor.Model {
    IntradayPresenter presenter;

    public IntradayModel(IntradayPresenter intradayPresenter) {
        presenter = intradayPresenter;
    }

    @Override
    public void getSymbolDetailsFromDB() {
        DatabaseHelper db = new DatabaseHelper(BaseApplication.getContext());
        presenter.onSymbolFetched(db.getSymbolList());
    }
}
