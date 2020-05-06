package com.pullrueepe.ui.holdings.mvp;

import com.pullrueepe.BaseView;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.holdings.HoldingResult;

public class HoldingsContractor {
    public interface View extends BaseView {

        void onCreateView(HoldingResult holdingResult);

    }

    public interface Presenter {
        void onCreateView(String clientId);

        void Success(HoldingResult holdingResult);

        void Error(String error);

        void Error(int error);

        void close();

    }

    public interface Model {

        void GetHoldings(ApiInterface apiInterface, String clientId);

        void close();
    }
}
