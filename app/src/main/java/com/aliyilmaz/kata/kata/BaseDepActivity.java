package com.aliyilmaz.kata.kata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aliyilmaz.kata.kata.dependencycomponent.DaggerDependencyComponent;
import com.aliyilmaz.kata.kata.dependencycomponent.DependencyComponent;
import com.aliyilmaz.kata.kata.network.NetworkModule;

import java.io.File;

public class BaseDepActivity extends AppCompatActivity{
    DependencyComponent dependencyComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "http-header");
        dependencyComponent = DaggerDependencyComponent.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public DependencyComponent getDependencyComponent() {
        return dependencyComponent;
    }
}
