package com.aliyilmaz.kata.kata.dependencycomponent;


import com.aliyilmaz.kata.kata.mainactivity.MainActivity;
import com.aliyilmaz.kata.kata.mainactivity.MainActivityModule;
import com.aliyilmaz.kata.kata.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class, MainActivityModule.class})
public interface DependencyComponent {
    void inject(MainActivity mainActivity);
}
