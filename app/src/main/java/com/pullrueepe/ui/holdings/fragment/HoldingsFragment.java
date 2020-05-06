package com.pullrueepe.ui.holdings.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.pullrueepe.R;
import com.pullrueepe.base.BaseFragment;
import com.pullrueepe.model.holdings.HoldingResult;
import com.pullrueepe.ui.holdings.adapter.Adapter;
import com.pullrueepe.ui.holdings.mvp.HoldingsContractor;
import com.pullrueepe.ui.holdings.mvp.HoldingsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoldingsFragment extends BaseFragment implements HoldingsContractor.View {

    @BindView(R.id.recyclerView)
    RecyclerView holdingsList;
    private RecyclerView.LayoutManager manager;
    private Adapter adapter;

    String clientId;
    HoldingsPresenter holdingsPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.holdings_layout, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        holdingsPresenter = new HoldingsPresenter(this, appRepository);
      //  clientId = appRepository.getUserId();
        clientId="1";
        if (isNetworkConnected()) {
            holdingsPresenter.onCreateView(clientId);
        } else {
            showToast(R.string.no_internet_connection);
        }
    }



    @Override
    public void onCreateView(HoldingResult holdingResult) {
        holdingsList.setHasFixedSize(true);
        manager = new LinearLayoutManager(context);
        holdingsList.setLayoutManager(manager);
        adapter=new Adapter(holdingResult,context);
        holdingsList.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
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

    }
}
