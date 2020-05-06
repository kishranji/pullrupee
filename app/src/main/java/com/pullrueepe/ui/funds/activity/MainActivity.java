package com.pullrueepe.ui.funds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.pullrueepe.R;
import com.pullrueepe.base.BaseActivity;
import com.pullrueepe.ui.IntradayCalculator.fragment.IntradayCalculator;
import com.pullrueepe.ui.dashboard.DashboardFragment;
import com.pullrueepe.ui.holdings.activity.HoldingsActivity;
import com.pullrueepe.ui.holdings.fragment.HoldingsFragment;
import com.pullrueepe.ui.login.activity.Login;
import com.pullrueepe.ui.monthlyTarget.fragment.MonthlyFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SESSION_ID = "session_id";
    public static final String CLIENT_ID = "client_id";
    public static final String USERNAME = "user_name";
    public static final String FUNDS = "Funds";
    String sessionId, clientId, userName;
    Toolbar toolbar;
    DrawerLayout drawer;

    public static Intent newInstance(Context context, String sessionId, String clientId, String userName, String funds) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(SESSION_ID, sessionId);
        intent.putExtra(CLIENT_ID, clientId);
        intent.putExtra(USERNAME, userName);
        intent.putExtra(FUNDS, funds);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {
                hideKeyboard();

            }
        });

        clientId = getIntent().getStringExtra(CLIENT_ID);
        sessionId = getIntent().getStringExtra(SESSION_ID);
        userName = getIntent().getStringExtra(USERNAME);
        String funds = getIntent().getStringExtra(FUNDS);
        Log.d("funds", funds);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        navigationView.setNavigationItemSelectedListener(this);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName_txt);
        txtProfileName.setText("Welcome " + userName);


        if (funds != null && funds.equals(FUNDS)) {
            displaySelectedScreen(R.id.nav_funds);
        } else {
            //add this line to display menu1 when the activity is loaded
            displaySelectedScreen(R.id.nav_dashboard);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    public void hideKeyboard() {
        /*InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);*/

        InputMethodManager inputManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        /*
         * If no view is focused, an NPE will be thrown
         *
         * Maxim Dmitriev
         */
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_dashboard:
                //  currentScreenIndex = HOME;
                /*DashboardFragment homeFragment = HomeFragment.newInstance(base_id);
                homeFragment.setItemClicked(this);
                replaceFragment(ContainerLayout.getId(), homeFragment, "");*/
                fragment = new DashboardFragment();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.nav_funds:
                Bundle bundle = new Bundle();
                bundle.putString(CLIENT_ID, clientId);
                fragment = new FundsActivity();
                fragment.setArguments(bundle);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.nav_holdings:
                fragment = new HoldingsFragment();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;

            case R.id.nav_monthly_target:
                fragment = new MonthlyFragment();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.nav_calculator:
                fragment = new IntradayCalculator();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.nav_logout:
                appRepository.saveIsLoggedIn(false);
                // Logout
                logout();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void logout() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


}