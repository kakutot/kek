package com.example.tupkalenko.trainee.project.presentation.base;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;
import com.example.tupkalenko.trainee.project.domain.navigation.Router;

import java.util.Objects;

import androidx.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BaseContract.BaseView, R extends Router>
        implements BaseContract.BasePresenter<V> {

    private final static String TAG = BasePresenter.class.getName();

    @NonNull
    private final R router;

    @Nullable
    private CompositeDisposable compositeDisposable;

    @Nullable
    private V view;

    public BasePresenter(@NonNull R router) {
        this.router = router;
    }

    protected void clearResources() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        } else {
            Log.e(TAG, "Composite disposable can't be null!");
        }
    }

    protected void addDisposable(@NonNull Disposable disposable) {
        if (disposable != null) {
            compositeDisposable.add(disposable);
        } else {
            Log.e(TAG, "Composite disposable can't be null!");
        }
    }

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
        createResources();
    }

    private void createResources() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        view = null;
        clearResources();
    }

    @NonNull
    @Override
    public V getView() {
        return Objects.requireNonNull(view);
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @NonNull
    @Override
    public R getRouter() {
        return router;
    }
}