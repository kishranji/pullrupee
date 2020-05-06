package com.pullrueepe.ui.IntradayCalculator.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

import butterknife.BindView;

public class AddSellcalculator extends BaseFragment{

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intraday_calculator_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
