package com.example.tupkalenko.trainee.project.domain.repository;

import androidx.annotation.NonNull;

import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;

import java.util.List;

import io.reactivex.Single;

public interface RestaurantRepository {

    @NonNull
    Single<List<Restaurant>> getRestaurantsByCollectionId(@NonNull String restaurantName,
                                                          int collectionId, int start, int count);

    @NonNull
    Single<Restaurant> getRestaurant(int restaurantId);
}