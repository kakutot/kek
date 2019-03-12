package com.example.tupkalenko.trainee.project.presentation.navigation;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseRouter {

    @IdRes
    private final int frameId;

    @NonNull
    private final AppCompatActivity activity;

    @Nullable
    private final FragmentManager fragmentManager;

    protected BaseRouter(@NonNull AppCompatActivity activity, @IdRes int frameId) {
        this.activity = activity;
        this.frameId = frameId;
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    protected void add(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .commit();
    }

    protected void replace(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(frameId, fragment)
                .commit();
    }

    protected void navigateToActivity(@NonNull Intent activityIntent) {
        activity.startActivity(activityIntent);
    }

    @NonNull
    protected AppCompatActivity getActivity() {
        return activity;
    }

    @Nullable
    protected FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}