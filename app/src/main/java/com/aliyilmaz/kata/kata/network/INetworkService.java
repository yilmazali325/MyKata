package com.aliyilmaz.kata.kata.network;


import com.aliyilmaz.kata.kata.models.JSONModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;


public interface INetworkService {

    @Headers("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
    @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    Observable<List<JSONModel>> getJsonModelList();

}
