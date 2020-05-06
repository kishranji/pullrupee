package com.pullrueepe.ui.holdings.activity;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pullrueepe.R;
import com.pullrueepe.base.BaseActivity;
import com.pullrueepe.ui.holdings.fragment.HoldingsFragment;

public class HoldingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment = new HoldingsFragment();
        ft.replace(R.id.holdings_fragment_container_layout, fragment, "HoldingsFragment");
        ft.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_holdings;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}