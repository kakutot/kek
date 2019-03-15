package com.example.tupkalenko.trainee.project.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BaseContract.BasePresenter>
        extends Fragment implements BaseContract.BaseView<P> {

    @Nullable
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @NonNull
    public abstract P getPresenter();

    @LayoutRes
    protected abstract int getLayoutId();
}