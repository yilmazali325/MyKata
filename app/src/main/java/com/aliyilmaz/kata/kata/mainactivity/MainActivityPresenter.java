package com.aliyilmaz.kata.kata.mainactivity;

import com.aliyilmaz.kata.kata.models.JSONModel;
import com.aliyilmaz.kata.kata.network.NetworkError;
import com.aliyilmaz.kata.kata.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MainActivityPresenter {

    private final NetworkService networkService;
    private MainActivityView view;
    private CompositeSubscription subscriptions;

    @Inject
    public MainActivityPresenter(NetworkService networkService) {
        this.networkService = networkService;
        this.subscriptions = new CompositeSubscription();
    }

    public void getJsonModelList() {
        view.showProgressDialog();

        Subscription subscription = networkService.getJsonModelList(new NetworkService.GetJsonModelListCallback() {


            @Override
            public void onSuccess(List<JSONModel> jsonModelList) {
                view.removeProgressDialog();
                view.getJsonModelList(jsonModelList);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
