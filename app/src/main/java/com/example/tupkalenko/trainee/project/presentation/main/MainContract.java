package com.example.tupkalenko.trainee.project.presentation.main;

import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;

public interface MainContract {

    interface MainView extends BaseContract.BaseView<MainPresenter> {

    }

    interface MainPresenter extends BaseContract.BasePresenter<MainView> {

        void showCollectionsScreen();
    }
}