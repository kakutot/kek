package com.example.tupkalenko.trainee.project.domain.mock;

import com.example.tupkalenko.trainee.project.domain.entity.Collection;
import com.example.tupkalenko.trainee.project.domain.repository.CollectionRepository;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import io.reactivex.Single;

public class MockCollectionRepository implements CollectionRepository {

    @NonNull
    private Map<Pair<Integer, String>, Collection> collections;

    public MockCollectionRepository() {
        collections = new HashMap<>();
        initMockCollections();
    }

    private void initMockCollections() {
        for(int i = 0;i < 5;i++) {
            putMockCollection(i + 1,
                              "test" + (i + 1),
                              "title" + (i + 1),
                              (i + 1) * 3,
                              i + 1);
        }
        //to test query with count param
        for(int i = 0;i < 5;i++) {
            putMockCollection(i + 1,
                              "test" + (i + 1),
                              "title" + (i + 1),
                              (i + 1) * 3,
                              i + 1);
        }
    }

    private void putMockCollection(int collectionId,
                                   @NonNull String imageUrl,
                                   @NonNull String title,
                                   int resultsCount,
                                   int cityId) {
        Collection collection = new Collection();
        collection.setId(collectionId);
        collection.setImageUrl(imageUrl);
        collection.setResultsCount(resultsCount);
        collection.setTitle(title);
        String suffix = generateRandomSuffix();

        collections.put(Pair.create(cityId, suffix), collection);
    }

    private String generateRandomSuffix() {
        byte[] array = new byte[1];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }

    @NonNull
    public Single<List<Collection>> getCollectionsByCityId(int cityId) {
        List<Collection> result = new LinkedList<>();

        for(Pair<Integer, String> key : collections.keySet()) {
            if (key.first != null && key.first == cityId) {
                result.add(collections.get(key));
            }
        }

        if (!result.isEmpty()) {
            return Single.just(result);
        } else {
            return Single.error(new Throwable("No collections"));
        }
    }

    @NonNull
    public Single<List<Collection>> getCollectionsByCityId(int cityId, int count) {
        List<Collection> result = new LinkedList<>();
        int counter = 0;

        for(Map.Entry<Pair<Integer, String>, Collection> entry : collections.entrySet()) {
            if (entry.getKey().first != null &&
                entry.getKey().first == cityId &&
                counter++ < count) {
                result.add(entry.getValue());
            }
        }

        if (!result.isEmpty()) {
            return Single.just(result);
        } else {
            return Single.error(new Throwable("No collections"));
        }
    }
}