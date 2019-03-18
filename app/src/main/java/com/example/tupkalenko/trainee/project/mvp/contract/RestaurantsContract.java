package com.example.tupkalenko.trainee.project.mvp.contract;

import com.example.tupkalenko.trainee.project.domain.entity.Collection;
import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.mvp.BaseContract;

import java.util.List;

import androidx.annotation.NonNull;

public interface RestaurantsContract {

    interface RestaurantsView
            extends BaseContract.BaseView<RestaurantsPresenter> {

        void onRestaurantsListLoaded(@NonNull List<Restaurant> Restaurants);
    }

    interface RestaurantsPresenter
            extends BaseContract.BasePresenter<RestaurantsView> {

        void search(@NonNull String restaurantName,
                    @NonNull Collection collection,
                    int start,
                    int count);

        void showRestaurantDetails(@NonNull Restaurant restaurant);
    }
}