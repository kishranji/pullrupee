package com.pullrueepe.ui.funds.mvp;

import com.pullrueepe.BaseView;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.funds.Funds;

public interface FundsContractor {

    interface View extends BaseView {
        void onCreateView(Funds funds);

        void showAmountEmptyError();

        void showFundsResponse(Funds funds);

        void moveToCreateFunds();
        void createFundsSuccess();
    }

    interface Presenter {
        void onCreateView(String clientId, String sessionId);
        void submitButtonClicked(String clientId,int transactionType,String amount,String date);

        void Success(Funds funds);

        void Error(String error);

        void Error(int error);
        void createFundsRefresh();

        void close();

    }

    interface Model {

        void requestFunds(ApiInterface apiInterface, String clientId, String sessionId);
        void submitAmount(ApiInterface apiInterface,String clientId,int transactionType,String amount,String date);

        void close();
    }
}
