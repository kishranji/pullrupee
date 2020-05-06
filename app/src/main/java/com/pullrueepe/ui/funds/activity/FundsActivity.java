package com.pullrueepe.ui.funds.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pullrueepe.R;
import com.pullrueepe.base.BaseActivity;
import com.pullrueepe.base.BaseFragment;
import com.pullrueepe.data.source.AppDataSource;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.data.source.sharedpreference.AppPreferenceDataSource;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.funds.PullrupeeData;
import com.pullrueepe.ui.funds.mvp.FundsContractor;
import com.pullrueepe.ui.funds.mvp.FundsPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FundsActivity extends BaseFragment implements FundsContractor.View, View.OnClickListener {

    @BindView(R.id.txt_curnt_investment)
    AppCompatTextView txtCurrentInvestemnt;

    @BindView(R.id.txt_curnt_value)
    AppCompatTextView txtCurrentValue;

    @BindView(R.id.txt_profit_loss)
    AppCompatTextView txtProfitLoss;

    @BindView(R.id.txt_profit_loss_in_prcnt)
    AppCompatTextView txtProfitLossinPercent;

    @BindView(R.id.radioGroup)
    RadioGroup transactionRadioGp;

    @BindView(R.id.radio_withdraw)
    RadioButton radioWithdraw;

    @BindView(R.id.radio_deposit)
    RadioButton radioDeposit;

    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;

    @BindView(R.id.amount_edt)
    AppCompatEditText edAmount;

    String sessionId, clientId;
    FundsPresenter fundsPresenter;

    public static final String CLIENT_ID = "client_id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_funds, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        fundsPresenter = new FundsPresenter(this, appRepository);
        if (isNetworkConnected()) {
            clientId = appRepository.getUserId();
            sessionId = appRepository.getSessionId();
            Log.d("clientId", clientId);
            Log.d("sessionId", sessionId);
            fundsPresenter.onCreateView(clientId, sessionId);
        } else {
            showToast(R.string.no_internet_connection);
        }
        btnSubmit.setOnClickListener(this);

    }


    @Override
    public void onCreateView(Funds funds) {
        if (funds != null && funds.getPullrupeedata() != null && funds.getPullrupeedata().size() > 0) {
            ArrayList<PullrupeeData> pullrupeeData = funds.getPullrupeedata();
            double currentinvestment = 0;
            double currentValue = 0;
            double profitloss = 0;
            double profitlosspercentage = 0;
            Log.d("Size", "" + pullrupeeData.size());
            for (int i = 0; i < pullrupeeData.size(); i++) {
                currentinvestment = funds.getPullrupeedata().get(i).getCurrentinvestment();
                currentValue = funds.getPullrupeedata().get(i).getCurrentvalue();
                profitloss = funds.getPullrupeedata().get(i).getProfitloss();
                profitlosspercentage = funds.getPullrupeedata().get(i).getProfitlosspercentage();
            }
            txtCurrentInvestemnt.setText(getString(R.string.Rs) + "" + String.format("%.2f", currentinvestment));
            txtCurrentValue.setText(getString(R.string.Rs) + "" + String.format("%.2f", currentValue));
            txtProfitLoss.setText(getString(R.string.Rs) + "" + String.format("%.2f", profitloss));
            txtProfitLossinPercent.setText("%" + String.format("%.2f", profitlosspercentage));
            if (profitloss < 0)
                txtProfitLoss.setTextColor(getResources().getColor(R.color.color_red));
            else
                txtProfitLoss.setTextColor(getResources().getColor(R.color.colorGreen));
            if (profitlosspercentage < 0)
                txtProfitLossinPercent.setTextColor(getResources().getColor(R.color.color_red));
            else
                txtProfitLossinPercent.setTextColor(getResources().getColor(R.color.colorGreen));
        }

    }

    @Override
    public void showAmountEmptyError() {
        Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT);
    }

    @Override
    public void showFundsResponse(Funds funds) {

    }

    @Override
    public void moveToCreateFunds() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void createFundsSuccess() {
        edAmount.setText("");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void showLoadingView() {
        showProgress();
    }

    @Override
    public void hideLoadingView() {
        hideProgress();
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void showError(int message) {
        showToast("" + message);
    }


    @Override
    public void onClick(View v) {
        if (isNetworkConnected()) {
            String amount = edAmount.getText().toString().trim();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            int transactionType = 0;
            if (radioWithdraw.isChecked())
                transactionType = 1;
            else if (radioDeposit.isChecked())
                transactionType = 0;
            if (amount.isEmpty()) {
                showAmountEmptyError();
            } else if (amount.equals("0")) {
            } else
                hideKeyboard();
            fundsPresenter.submitButtonClicked(clientId, transactionType, amount, date);
        } else
            showToast(R.string.no_internet_connection);


    }
}

