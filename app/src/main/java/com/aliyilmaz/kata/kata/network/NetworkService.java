package com.aliyilmaz.kata.kata.network;


import com.aliyilmaz.kata.kata.models.JSONModel;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class NetworkService {
    private INetworkService INetworkService;


    public NetworkService(INetworkService INetworkService) {
        this.INetworkService = INetworkService;
    }

    public Subscription getJsonModelList(final GetJsonModelListCallback callback) {

        return INetworkService.getJsonModelList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<JSONModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<JSONModel> jsonModelList) {
                        callback.onSuccess(jsonModelList);

                    }
                });
    }

    public interface GetJsonModelListCallback {
        void onSuccess(List<JSONModel> jsonModelList);

        void onError(NetworkError networkError);
    }
}
