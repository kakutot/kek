package com.example.tupkalenko.trainee.project.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tupkalenko.trainee.project.R;
import com.example.tupkalenko.trainee.project.mvp.contract.MainContract;
import com.example.tupkalenko.trainee.project.ui.BaseActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.MainPresenter>
        implements MainContract.MainView {

    //TODO inject
    @NonNull
    private MainContract.MainPresenter presenter;

    @BindView(R.id.fragmentTv)
    TextView fragmentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.attachView(this);
        presenter.showCollectionsScreen();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected MainContract.MainPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void showUnexpectedError(@NonNull Throwable throwable) {
        new AlertDialog.Builder(this)
                       .setMessage(R.string.error_message_default)
                       .setPositiveButton(R.string.error_message_confirm, null)
                       .show();
    }
}