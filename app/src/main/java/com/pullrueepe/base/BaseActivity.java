package com.pullrueepe.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.pullrueepe.R;
import com.pullrueepe.data.source.AppDataSource;
import com.pullrueepe.data.source.AppRepository;
import com.pullrueepe.data.source.sharedpreference.AppPreferenceDataSource;
import com.pullrueepe.util.NetworkUtils;
import com.pullrueepe.util.ProgressDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected ProgressDialog progressDialog;
    protected AppRepository appRepository;
    protected AppDataSource appDataSource;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        appDataSource = new AppPreferenceDataSource(this);
        appRepository = new AppRepository(appDataSource);
        context = this;
        //progressDialog = new ProgressDialog(context);
    }

    public abstract @LayoutRes
    int getLayout();


    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showProgress() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading_wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
        /*if (progressDialog != null)
            progressDialog.dismiss();*/
    }


    public void setupToolBar() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                hideKeyboard();
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(@StringRes int strRes) {
        Toast.makeText(this, strRes, Toast.LENGTH_SHORT).show();
    }


    public void changeActivity(Class clz) {
        Intent i = new Intent(this, clz);
        startActivity(i);
    }

    public void changeActivityClearBackStack(Class clz) {
        Intent i = new Intent(this, clz);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


}