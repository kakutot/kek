package com.example.tupkalenko.trainee.project.domain.mock;

import com.example.tupkalenko.trainee.project.domain.entity.Location;
import com.example.tupkalenko.trainee.project.domain.entity.Restaurant;
import com.example.tupkalenko.trainee.project.domain.entity.UserRating;
import com.example.tupkalenko.trainee.project.domain.repository.RestaurantRepository;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import io.reactivex.Single;

public class MockRestaurantRepository implements RestaurantRepository {

    @NonNull
    private final Map<Pair<Integer, Pair<String, Integer>>, Restaurant> restaurants;

    public MockRestaurantRepository() {
        this.restaurants = new HashMap<>();
        initMockRestaurants();
    }

    private void initMockRestaurants() {
        for(int i = 0; i < 5 ; i++) {
            putMockRestaurant(i + 1,
                              "test" + (i + 1),
                              new Location(),
                              (float)i,
                              "$",
                              generateRandomString(i * 4),
                              new UserRating());

            putMockRestaurantWithCollectionId(i + 1,
                              "test" + (i + 1),
                              new Location(),
                              (float)i,
                              "$",
                              generateRandomString(i * 4),
                              new UserRating(),
                              i + 1);
        }
    }

    private void putMockRestaurant(int restaurantId,
                                   @NonNull String restaurantName,
                                   @Nullable Location location,
                                   float averageCostForTwo,
                                   @Nullable String currency,
                                   @Nullable String featuredImage,
                                   @Nullable UserRating userRating) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName(restaurantName);
        restaurant.setLocation(location);
        restaurant.setAverageCostForTwo(averageCostForTwo);
        restaurant.setCurrency(currency);
        restaurant.setFeaturedImage(featuredImage);
        restaurant.setUserRating(userRating);

        restaurants.put(Pair.create(restaurantId, null), restaurant);
    }

    private void putMockRestaurantWithCollectionId(int restaurantId,
                                                   @NonNull String restaurantName,
                                                   @Nullable Location location,
                                                   float averageCostForTwo,
                                                   @Nullable String currency,
                                                   @Nullable String featuredImage,
                                                   @Nullable UserRating userRating,
                                                   int collectionId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName(restaurantName);
        restaurant.setLocation(location);
        restaurant.setAverageCostForTwo(averageCostForTwo);
        restaurant.setCurrency(currency);
        restaurant.setFeaturedImage(featuredImage);
        restaurant.setUserRating(userRating);

        restaurants.put(Pair.create(null, Pair.create(restaurantName, collectionId)), restaurant);
    }

    private String generateRandomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }

    @NonNull
    @Override
    public Single<List<Restaurant>> getRestaurantsByCollectionId(@NonNull String restaurantName,
                                                                 int collectionId,
                                                                 int start,
                                                                 int count) {
        List<Restaurant> result = new LinkedList<>();
        int counter = 0;
        int curPos = 0;

        for(Map.Entry<Pair<Integer, Pair<String, Integer>>, Restaurant> entry : restaurants.entrySet()) {
            if (start > curPos++) {
                continue;
            }

            if (entry.getKey().second != null &&
                entry.getKey().second.first != null &&
                entry.getKey().second.first.equals(restaurantName) &&
                entry.getKey().second.second != null &&
                entry.getKey().second.second == collectionId &&
                counter++ < count) {
                result.add(entry.getValue());
            }
        }

        if (!result.isEmpty()) {
            return Single.just(result);
        } else {
            return Single.error(new Throwable("No restaurants"));
        }
    }

    @NonNull
    @Override
    public Single<Restaurant> getRestaurant(int restaurantId) {
        Pair<Integer, Pair<String, Integer>> key = Pair.create(restaurantId, null);

        if (restaurants.containsKey(key)) {
            return Single.just(restaurants.get(key));
        }

        return Single.error(new Throwable("No restaurant"));
    }
}