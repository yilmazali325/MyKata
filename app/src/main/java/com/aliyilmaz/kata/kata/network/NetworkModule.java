package com.aliyilmaz.kata.kata.network;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    File cacheFile;
    private static final String BASE_URL = "https://www.abercrombie.com/";

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        // Create cache object
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create cache control and specify cache max age
        final CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(5, TimeUnit.DAYS) // 15 minutes cache
                .build();

        // Create okhttpclient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request for cache
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control",cacheControl.toString() )
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();

                        return response;
                    }
                })
                .cache(cache)
                .build();

        // return retrofit object
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public INetworkService providesNetworkService(Retrofit retrofit) {
        return retrofit.create(INetworkService.class);
    }
    @Provides
    @Singleton
    public NetworkService providesService(INetworkService INetworkService) {
        return new NetworkService(INetworkService);
    }

}
