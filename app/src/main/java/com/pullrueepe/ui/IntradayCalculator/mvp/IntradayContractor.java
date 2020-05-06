package com.pullrueepe.ui.IntradayCalculator.mvp;

import com.pullrueepe.BaseView;
import com.pullrueepe.model.symbol.SymbolData;

import java.util.ArrayList;

public interface IntradayContractor {
    interface View extends BaseView {

        void populateSymbol(ArrayList<SymbolData> symbolList);
    }

    interface Presenter {
        void onViewCreated();

        void onSymbolFetched(ArrayList<SymbolData> symbolList);
    }

    interface Model {
       void  getSymbolDetailsFromDB();
    }
}
