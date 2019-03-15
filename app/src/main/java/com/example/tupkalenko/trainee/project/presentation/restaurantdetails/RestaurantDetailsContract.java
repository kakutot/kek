package com.example.tupkalenko.trainee.project.presentation.restaurantdetails;

import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.domain.mvpbase.BaseContract;

import androidx.annotation.NonNull;

public interface RestaurantDetailsContract {

    interface RestaurantsDetailsView extends
            BaseContract.BaseView<RestaurantDetailsContract.RestaurantDetailsPresenter> {

        void onRestaurantLoaded(@NonNull Restaurant restaurant);
    }

    interface RestaurantDetailsPresenter
            extends BaseContract.BasePresenter<RestaurantDetailsContract.RestaurantsDetailsView> {

        void loadDetails(@NonNull Restaurant restaurant);
    }
}