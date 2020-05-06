package com.pullrueepe.ui.monthlyTarget.mvp;

import com.pullrueepe.BaseView;
import com.pullrueepe.data.rest.ApiInterface;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetResult;
import com.pullrueepe.model.monthlyTarget.ParticularMonthTarget;

import java.util.List;

public interface MonthlyTargetContractor {

    interface View extends BaseView {
        void onCreateedView(MonthlyTargetResult monthlyTarget);

        void showAmountEmptyError();

        void showFundsResponse(Funds funds);

        void moveToCreateFunds();

        void createMonthlySuccess();
        void showTargetAmount(ParticularMonthTarget particularMonthTarget);

        void monthlyTargetHistory(GetAllMonthlyTarget getAllMonthlyTarget);
    }

    interface Presenter {
        void onCreateView(String clientId, int tradeYear, int tradeMonth);

        void submitButtonClicked(String clientId, int tradeYear, int tradeMonth, String amount);

        void Success(MonthlyTargetResult monthlyTarget);

        void Error(String error);

        void Error(int error);

        void createMonthlyRefresh();

        void close();

        void onSetTargetAmount(String clientId, int tradeYear, int tradeMonth,String actionButton);

        void getTagrgetAamount(ParticularMonthTarget particularMonthTarget);

        void onretriveHistory(String clientId, int tradeYear);
        void monthlyTargetHistory(GetAllMonthlyTarget monthlyTarget);
    }

    interface Model {

        void requestMonthlyTarget(ApiInterface apiInterface, String clientId, int tradeYear, int tradeMonth);

        void submitAmount(ApiInterface apiInterface, String clientId, int tradeYear, int tradeMonth, String amount);

        void setTargetAmount(ApiInterface apiInterface, String clientId, int tradeYear, int tardeMonth,String actionButton);
        void onRetriveHistory(ApiInterface apiInterface,String clientId, int tradeYear);
        void close();
    }
}
