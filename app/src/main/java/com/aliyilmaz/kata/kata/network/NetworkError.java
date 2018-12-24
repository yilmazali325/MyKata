package com.aliyilmaz.kata.kata.network;

import android.util.Log;
import java.io.IOException;
import retrofit2.adapter.rxjava.HttpException;


public class NetworkError extends Throwable {
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static final String NETWORK_ERROR_MESSAGE = "No Internet Connection!";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    private final Throwable error;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }


    public String getAppErrorMessage() {
        if (this.error instanceof IOException){
            Log.d(ERROR_MESSAGE_HEADER, DEFAULT_ERROR_MESSAGE);
            return NETWORK_ERROR_MESSAGE;
        }
        if (!(this.error instanceof HttpException)) {
            Log.d(ERROR_MESSAGE_HEADER, DEFAULT_ERROR_MESSAGE);
            return DEFAULT_ERROR_MESSAGE;
        }
        return DEFAULT_ERROR_MESSAGE;
    }


}
