package com.example.tupkalenko.trainee.project.presentation.restaurants;

import androidx.annotation.NonNull;

import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;

import java.util.List;

public interface RestaurantsContract {

    interface RestaurantsView
            extends BaseContract.BaseView<RestaurantsContract.RestaurantsPresenter> {

        void onRestaurantsListLoaded(@NonNull List<Restaurant> Restaurants);
    }

    interface RestaurantsPresenter
            extends BaseContract.BasePresenter<RestaurantsContract.RestaurantsView> {

        void search(int collectionId, int start, int count);
    }
}