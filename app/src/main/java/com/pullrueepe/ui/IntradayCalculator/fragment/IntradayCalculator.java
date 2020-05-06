package com.pullrueepe.ui.IntradayCalculator.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.pullrueepe.R;
import com.pullrueepe.base.BaseFragment;
import com.pullrueepe.model.symbol.SymbolData;
import com.pullrueepe.ui.IntradayCalculator.mvp.IntradayContractor;
import com.pullrueepe.ui.IntradayCalculator.mvp.IntradayPresenter;
import com.pullrueepe.ui.holdings.mvp.HoldingsPresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class IntradayCalculator extends BaseFragment implements IntradayContractor.View {

    @BindView(R.id.symbol_spinner)
    Spinner symbolSpinner;
    @BindView(R.id.radioGroup)
    RadioGroup buySellRg;
    @BindView(R.id.buy_qty_edt)
    EditText buyQtyEd;
    @BindView(R.id.buy_price_edt)
    EditText buyPriceEd;
    @BindView(R.id.radioGroup1)
    RadioGroup statusRg;
    @BindView(R.id.btn_submit)
    Button submitBtn;
    IntradayPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intraday_calculator_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        presenter = new IntradayPresenter(this, appRepository);
        //  clientId = appRepository.getUserId();

        if (isNetworkConnected()) {
            presenter.onViewCreated();

        } else {
            showToast(R.string.no_internet_connection);
        }
    }

    @Override
    public void populateSymbol(ArrayList<SymbolData> symbolList) {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showError(int message) {

    }
}
