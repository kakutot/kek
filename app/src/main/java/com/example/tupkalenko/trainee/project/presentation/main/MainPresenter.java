package com.example.tupkalenko.trainee.project.presentation.main;

import com.example.tupkalenko.trainee.project.domain.navigation.MainRouter;
import com.example.tupkalenko.trainee.project.presentation.base.BasePresenter;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;

public class MainPresenter
        extends BasePresenter<MainContract.MainView, MainRouter>
        implements MainContract.MainPresenter {

    public MainPresenter(@NonNull Scheduler backgroundScheduler,
                         @NonNull Scheduler foregroundScheduler,
                         @NonNull MainRouter router) {
        super(backgroundScheduler, foregroundScheduler, router);
    }

    @Override
    public void showCollectionsScreen() {
        getRouter().showCollectionsScreen();
    }
}