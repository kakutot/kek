package com.example.tupkalenko.trainee.project.presentation.base;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;

public abstract class BaseActivity<P extends BaseContract.BasePresenter> extends AppCompatActivity {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract P getPresenter();
}