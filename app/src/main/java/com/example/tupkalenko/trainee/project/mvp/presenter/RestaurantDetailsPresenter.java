package com.example.tupkalenko.trainee.project.mvp.presenter;

import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.domain.navigation.contract.RestaurantDetailsScreenRouter;
import com.example.tupkalenko.trainee.project.domain.repository.RestaurantRepository;
import com.example.tupkalenko.trainee.project.mvp.BasePresenter;
import com.example.tupkalenko.trainee.project.mvp.contract.RestaurantDetailsContract;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class RestaurantDetailsPresenter
        extends BasePresenter<RestaurantDetailsContract.RestaurantsDetailsView, RestaurantDetailsScreenRouter>
        implements RestaurantDetailsContract.RestaurantDetailsPresenter {

    @NonNull
    private final RestaurantRepository restaurantRepository;

    public RestaurantDetailsPresenter(@NonNull Scheduler backgroundScheduler,
                                      @NonNull Scheduler foregroundScheduler,
                                      @NonNull RestaurantDetailsScreenRouter router,
                                      @NonNull RestaurantRepository restaurantRepository) {
        super(backgroundScheduler, foregroundScheduler, router);
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void loadDetails(@NonNull Restaurant restaurant) {
        Single chain = restaurantRepository.getRestaurant(restaurant.getId())
                .doOnSubscribe(disposable -> getView()
                .showLoading())
                .doOnTerminate(() -> getView()
                .hideLoading());

        subscribe(chain, this::onSuccess, this::onError);
    }

    private void onSuccess(@NonNull Restaurant restaurant) {
        if (isViewAttached()) {
            getView().onRestaurantLoaded(restaurant);
        }
    }
}