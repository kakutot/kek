package com.example.tupkalenko.trainee.project.presentation.restaurants;

import android.util.Log;

import com.example.tupkalenko.trainee.project.domain.entity.Collection;
import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.domain.navigation.RestaurantsScreenRouter;
import com.example.tupkalenko.trainee.project.domain.repository.RestaurantRepository;
import com.example.tupkalenko.trainee.project.presentation.base.BasePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class RestaurantsPresenter
        extends BasePresenter<RestaurantsContract.RestaurantsView, RestaurantsScreenRouter>
        implements RestaurantsContract.RestaurantsPresenter {

    private final static String TAG = RestaurantsPresenter.class.getName();

    @NonNull
    private final RestaurantRepository restaurantRepository;

    public RestaurantsPresenter(@NonNull Scheduler backgroundScheduler,
                                @NonNull Scheduler foregroundScheduler,
                                @NonNull RestaurantsScreenRouter router,
                                @NonNull RestaurantRepository restaurantRepository) {
        super(backgroundScheduler, foregroundScheduler, router);
        this.restaurantRepository = restaurantRepository;
    }

    private void onSuccess(@NonNull List<Restaurant> restaurants) {
        if (isViewAttached()) {
            getView().onRestaurantsListLoaded(restaurants);
        }
    }

    @Override
    public void search(@NonNull String restaurantName,
                       @NonNull Collection collection, int start, int count) {
        if (start >= 0 && count > 0) {
            Single chain = restaurantRepository.getRestaurantsByCollectionId(restaurantName,
                                                                             collection.getId(),
                                                                             start,
                                                                             count)
                            .doOnSubscribe(disposable -> getView()
                            .showLoading())
                            .doOnTerminate(() -> getView()
                            .hideLoading());

            subscribe(chain, this::onSuccess, this::onError);
        } else {
            Log.e(TAG, "Bad start or count value");
        }
    }

    @Override
    public void showRestaurantDetails(@NonNull Restaurant restaurant) {
        getRouter().showRestaurantDetails(restaurant);
    }
}