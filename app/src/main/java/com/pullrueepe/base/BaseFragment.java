package com.pullrueepe.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.pullrueepe.R;
import com.pullrueepe.data.source.AppDataSource;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.data.source.sharedpreference.AppPreferenceDataSource;
import com.pullrueepe.util.NetworkUtils;

import butterknife.ButterKnife;

import static android.support.v4.content.ContextCompat.getSystemService;

public abstract class BaseFragment extends Fragment {
    protected AppRepository appRepository;
    protected AppDataSource appDataSource;
    protected Context context;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(getActivity());
        appDataSource = new AppPreferenceDataSource(getActivity());
        appRepository = new AppRepository(appDataSource);

    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(true);
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getContext());
    }



    public void showToast(@StringRes int strRes) {
        Toast.makeText(getContext(), strRes, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.loading_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgress() {
        if(progressDialog!=null && progressDialog.isShowing())
        progressDialog.dismiss();
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
