package com.example.tupkalenko.trainee.project.ui;

import android.os.Bundle;

import com.example.tupkalenko.trainee.project.mvp.BaseContract;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BaseContract.BasePresenter>
        extends AppCompatActivity {

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