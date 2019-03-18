package com.example.tupkalenko.trainee.project.mvp.contract;

import com.example.tupkalenko.trainee.project.domain.entity.Collection;
import com.example.tupkalenko.trainee.project.mvp.BaseContract;

import java.util.List;

import androidx.annotation.NonNull;

public interface CollectionsContract {

    interface CollectionsView extends
            BaseContract.BaseView<CollectionsPresenter> {

        void onCollectionsLoaded(@NonNull List<Collection> collections);
    }

    interface CollectionsPresenter extends
            BaseContract.BasePresenter<CollectionsView> {

        void showRestaurantsScreen(@NonNull Collection collection);

        void search(@NonNull String cityName);
    }
}