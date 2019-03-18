package com.example.tupkalenko.trainee.project;

import android.util.Log;

import com.example.tupkalenko.trainee.project.presentation.base.BasePresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxExecutor {

    private final static String TAG = BasePresenter.class.getName();

    @NonNull
    private final Scheduler backgroundScheduler;

    @NonNull
    private final Scheduler foregroundScheduler;

    @Nullable
    private CompositeDisposable compositeDisposable;

    public RxExecutor(@NonNull Scheduler backgroundScheduler,
                      @NonNull Scheduler foregroundScheduler) {
        this.backgroundScheduler = backgroundScheduler;
        this.foregroundScheduler = foregroundScheduler;
    }

    public <T> void subscribe(@NonNull Single<T> chain,
                              @NonNull Consumer<T> onSuccess,
                              @NonNull Consumer<Throwable> onError) {
        Disposable disposable = chain.subscribeOn(backgroundScheduler)
                .observeOn(foregroundScheduler)
                .subscribe(onSuccess, onError);
        addDisposable(disposable);
    }

    protected void initResources() {
        compositeDisposable = new CompositeDisposable();
    }

    protected void clearResources() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    private void addDisposable(@NonNull Disposable disposable) {
        if (disposable != null) {
            compositeDisposable.add(disposable);
        } else {
            Log.e(TAG, "Composite disposable can't be null!");
        }
    }
}