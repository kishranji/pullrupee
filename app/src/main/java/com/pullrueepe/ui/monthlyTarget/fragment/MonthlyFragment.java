package com.pullrueepe.ui.monthlyTarget.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pullrueepe.R;
import com.pullrueepe.base.BaseFragment;
import com.pullrueepe.model.funds.Funds;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.MonthlyTargetResult;
import com.pullrueepe.model.monthlyTarget.ParticularMonthTarget;
import com.pullrueepe.model.monthlyTarget.Pullrupeedatum;
import com.pullrueepe.ui.monthlyTarget.adapter.HistoryAdapter;
import com.pullrueepe.ui.monthlyTarget.mvp.MonthlyTargetContractor;
import com.pullrueepe.ui.monthlyTarget.mvp.MonthlyTargetPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MonthlyFragment extends BaseFragment implements View.OnClickListener, MonthlyTargetContractor.View {
    @BindView(R.id.txt_target)
    AppCompatTextView targetTextView;
    @BindView(R.id.txt_days_hand)
    AppCompatTextView daysHandTextView;
    @BindView(R.id.txt_per_day_required)
    AppCompatTextView perdayRequiredTextView;
    @BindView(R.id.txt_yet_to_do)
    AppCompatTextView yettodotTextView;
    @BindView(R.id.txt_sofarDone)
    AppCompatTextView sofardoneTextView;
    @BindView(R.id.ed_target_amount)
    AppCompatEditText targetAmountEdt;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;
    MonthlyTargetPresenter monthlyTargetPresenter;
    @BindView(R.id.target_amount)
    AppCompatTextView targetAmount;
    @BindView(R.id.btn_current)
    AppCompatButton txtCurrent;
    @BindView(R.id.btn_next)
    AppCompatButton btnNext;
    @BindView(R.id.btn_previous)
    AppCompatButton btnPrevious;

    @BindView(R.id.target_guid)
    LinearLayout targetLayout;

    @BindView(R.id.title_txt)
    AppCompatTextView titleTxt;



    @BindView(R.id.recyclerView_history_list)
    RecyclerView historyList;
    List<AllMonthlyTargets> monthlyTargetList = new ArrayList<>();

    String clientId;
    public static final String CLIENT_ID = "client_id";
    Calendar instance;
    int tradeMonth, tradeYear;
    HistoryAdapter historyAdapter;
    String actionButton = "";
    int cMonth = 0;
    int particularMonth = 0;

    ArrayList<Pullrupeedatum> pullrupeedata = new ArrayList<>();
    String monthTarget = "0";
    String dayinHand = "0";
    String perDayRequired = "0";
    String soFarDone = "0";
    String yettoDo = "0";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monthlyTargetPresenter = new MonthlyTargetPresenter(this, appRepository);


        clientId = appRepository.getUserId();
        instance = Calendar.getInstance();
        tradeMonth = instance.get(Calendar.MONTH);
        tradeYear = instance.get(Calendar.YEAR);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("MMM");
        String month = simpleDateFormat.format(date);
        Date date1 = new Date();
        simpleDateFormat.applyPattern("yyyy");
        System.out.println("Year  : " + simpleDateFormat.format(date1));
        txtCurrent.setText(month + "-" + simpleDateFormat.format(date1));


        if (isNetworkConnected()) {
            monthlyTargetPresenter.onCreateView(clientId, tradeYear, tradeMonth + 1);
            monthlyTargetPresenter.onSetTargetAmount(clientId, tradeYear, cMonth, actionButton);
            monthlyTargetPresenter.onretriveHistory(clientId, tradeYear);
        } else showToast(R.string.no_internet_connection);
        Log.d("MonthlyFragment", "" + monthlyTargetList);


        btnSubmit.setOnClickListener(this);
        txtCurrent.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.monthly_target_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_current:
                btnSubmit.setEnabled(true);
                titleTxt.setVisibility(View.VISIBLE);
                targetLayout.setVisibility(View.VISIBLE);
                targetAmountEdt.setEnabled(true);
                actionButton = "";
                if (isNetworkConnected()) {
                    monthlyTargetPresenter.onSetTargetAmount(clientId, tradeYear, particularMonth, actionButton);

                } else {
                    showToast(R.string.no_internet_connection);
                }

            case R.id.btn_submit:
                String targetamount = targetAmountEdt.getText().toString().trim();
                if (isNetworkConnected()) {
                    if (targetamount.isEmpty())
                        showAmountEmptyError();
                    else if (targetamount.equals("0")) {
                    } else
                        monthlyTargetPresenter.submitButtonClicked(clientId, tradeYear, tradeMonth + 1, targetamount);

                } else {
                    showToast(R.string.no_internet_connection);
                }
                break;
            case R.id.btn_next:
                targetAmountEdt.setEnabled(false);
                btnSubmit.setEnabled(false);

                actionButton = "N";
                if (isNetworkConnected()) {

                    monthlyTargetPresenter.onSetTargetAmount(clientId, tradeYear, particularMonth, actionButton);
                    hideLayout();
                } else {
                    showToast(R.string.no_internet_connection);
                }
                hideLayout();
                break;
            case R.id.btn_previous:
                btnSubmit.setEnabled(false);
                targetAmountEdt.setEnabled(false);
                actionButton = "P";
                if (isNetworkConnected()) {
                    monthlyTargetPresenter.onSetTargetAmount(clientId, tradeYear, particularMonth, actionButton);
                    hideLayout();
                } else {
                    showToast(R.string.no_internet_connection);
                }
                hideLayout();

                break;

        }


    }

    public void hideLayout() {
        //  parentLayout = (LinearLayout) targetLayout.getParent();
        targetLayout.setVisibility(View.GONE);
        // LinearLayout txtTitle = (LinearLayout) titleTxt.getParent();
        titleTxt.setVisibility(View.GONE);
    }

    @Override
    public void onCreateedView(MonthlyTargetResult monthlyTarget) {



        Log.d("sizeof month", "" + monthlyTarget.getPullrupeedata().size());

        if (monthlyTarget.getPullrupeedata().size() != 0) {
            for (int i = 0; i <  monthlyTarget.getPullrupeedata().size(); i++) {
                monthTarget = monthlyTarget.getPullrupeedata().get(i).getCurrentmonthtarget();
                dayinHand = monthlyTarget.getPullrupeedata().get(i).getCurrentmonthdaysinhand();
                perDayRequired = monthlyTarget.getPullrupeedata().get(i).getCurrentmonthperdayrequired();
                soFarDone = monthlyTarget.getPullrupeedata().get(i).getCurrentmonthsofardone();
                yettoDo = monthlyTarget.getPullrupeedata().get(i).getCurrentmonthyettodo();

            }
        }

            targetTextView.setText(getString(R.string.Rs) + "" + monthTarget);
            daysHandTextView.setText(dayinHand);
            perdayRequiredTextView.setText(getString(R.string.Rs) + "" + perDayRequired);
            sofardoneTextView.setText(getString(R.string.Rs) + "" + soFarDone);
            yettodotTextView.setText(getString(R.string.Rs) + "" + yettoDo);



    }    @Override
    public void showAmountEmptyError() {
        Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT);
    }

    @Override
    public void showFundsResponse(Funds funds) {

    }

    @Override
    public void moveToCreateFunds() {

    }

    @Override
    public void createMonthlySuccess() {
        targetAmountEdt.setText("");
        hideKeyboard();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
        showToast("Monthly Target updated successfully");
    }

    @Override
    public void showTargetAmount(ParticularMonthTarget particularMonthTarget) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("MMM");
        String month = simpleDateFormat.format(date);
        Date date1 = new Date();
        simpleDateFormat.applyPattern("yyyy");

        if (particularMonthTarget != null) {
            this.particularMonth = Integer.parseInt(particularMonthTarget.getPullrupeedata().get(0).getMonth());
            String dispalyMonth=particularMonthTarget.getPullrupeedata().get(0).getDisplaymonth();
            txtCurrent.setText(dispalyMonth);
            if(dispalyMonth.equals(month + "-" + simpleDateFormat.format(date1)))
            {
                targetAmountEdt.setEnabled(true);
                btnSubmit.setEnabled(true);
                targetLayout.setVisibility(View.VISIBLE);
                titleTxt.setVisibility(View.VISIBLE);
            }
            targetAmount.setText(getString(R.string.Rs) + "" + particularMonthTarget.getPullrupeedata().get(0).getTargetamount());


        }
    }

    @Override
    public void monthlyTargetHistory(GetAllMonthlyTarget getAllMonthlyTarget) {
        //  ArrayList<AllMonthlyTargets> monthlyTargets=new ArrayList<>();
        //  monthlyTargetList=getAllMonthlyTarget;
        historyList.setHasFixedSize(true);
        historyList.setLayoutManager(new LinearLayoutManager(context));
        historyAdapter = new HistoryAdapter(context, getAllMonthlyTarget);
        historyList.setAdapter(historyAdapter);
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
showToast(message);
    }
}
