package com.gvmarc.tvshows.presentation.home;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gvmarc.tvshows.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @LayoutRes
    int layout = R.layout.activity_home;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        ButterKnife.bind(this);

        initViews();

        if (savedInstanceState == null) {
            setUpFragment();
        }
    }

    private void initViews() {
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setUpFragment() {
        mHomeFragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomeFragment).commit();
    }
}
