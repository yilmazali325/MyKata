package com.aliyilmaz.kata.kata.mainactivity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.aliyilmaz.kata.kata.App;
import com.aliyilmaz.kata.kata.R;
import com.aliyilmaz.kata.kata.models.JSONModel;
import com.aliyilmaz.kata.kata.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends Activity implements MainActivityView {
    private static final String LOG_ONFAILURE = "LOG_ONFAILURE";
    private RecyclerView list;
    private ProgressBar progressBar;


    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App)getApplication()).getDependencyComponent().inject(this);

        initView();
        setRecyclerView();

        mainActivityPresenter.setView(this);
        mainActivityPresenter.getJsonModelList();
    }

    public  void initView(){
        setContentView(R.layout.activity_home);
        list = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progress);
    }

    public void setRecyclerView(){
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setItemViewCacheSize(20);
        list.setDrawingCacheEnabled(true);    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Log.d(LOG_ONFAILURE, appErrorMessage);
    }

    @Override
    public void getJsonModelList(List<JSONModel> jsonModel) {
        MainActivityAdapter adapter = new MainActivityAdapter(getApplicationContext(), jsonModel);
        list.setAdapter(adapter);

    }
}
