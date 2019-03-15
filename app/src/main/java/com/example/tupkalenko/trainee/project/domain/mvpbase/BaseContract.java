package com.example.tupkalenko.trainee.project.domain.mvpbase;

import androidx.annotation.NonNull;

import com.example.tupkalenko.trainee.project.domain.navigation.Router;

public interface BaseContract {

    interface BaseView<P extends BasePresenter> {

        void showLoading();

        void hideLoading();

        void showRetry();

        void hideRetry();

        void showUnexpectedError(@NonNull Throwable throwable);
    }

    interface BasePresenter<V extends BaseView> {

        void attachView(@NonNull V v);

        void detachView();

        @NonNull
        V getView();

        boolean isViewAttached();

        @NonNull
        Router getRouter();

        void navigateBack();

        void onError(@NonNull Throwable throwable);
    }
}