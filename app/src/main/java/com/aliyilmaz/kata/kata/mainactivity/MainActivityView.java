package com.aliyilmaz.kata.kata.mainactivity;

import com.aliyilmaz.kata.kata.models.JSONModel;

import java.util.List;

import javax.inject.Inject;


public interface MainActivityView {
    void showProgressDialog();

    void removeProgressDialog();

    void onFailure(String appErrorMessage);

    void getJsonModelList(List<JSONModel> jsonModelList);

}
