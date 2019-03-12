package com.example.tupkalenko.trainee.project.presentation.collections;

import com.example.tupkalenko.trainee.project.domain.entity.City;
import com.example.tupkalenko.trainee.project.domain.entity.Collection;
import com.example.tupkalenko.trainee.project.domain.navigation.CollectionsScreenRouter;
import com.example.tupkalenko.trainee.project.domain.repository.CityRepository;
import com.example.tupkalenko.trainee.project.domain.repository.CollectionRepository;
import com.example.tupkalenko.trainee.project.presentation.base.BasePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class CollectionsPresenter
        extends BasePresenter<CollectionsContract.CollectionsView, CollectionsScreenRouter>
        implements CollectionsContract.CollectionsPresenter {

    @NonNull
    private final CityRepository cityRepository;

    @NonNull
    private final CollectionRepository collectionRepository;

    public CollectionsPresenter(@NonNull Scheduler backgroundScheduler,
                                @NonNull Scheduler foregroundScheduler,
                                @NonNull CollectionsScreenRouter router,
                                @NonNull CollectionRepository collectionRepository,
                                @NonNull CityRepository cityRepository) {
        super(backgroundScheduler, foregroundScheduler, router);
        this.collectionRepository = collectionRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void search(@NonNull String cityName) {
            Single chain = cityRepository.getCity(cityName)
                            .doOnSubscribe(disposable -> getView().showLoading())
                            .map(City::getId)
                            .flatMap(collectionRepository::getCollectionsByCityId)
                            .doOnTerminate(() -> getView().hideLoading());

            subscribe(chain, this::onSuccess, this::onError);
    }

    private void onSuccess(@NonNull List<Collection> collections) {
        if(isViewAttached()) {
            getView().onCollectionsLoaded(collections);
        }
    }

    @Override
    public void showRestaurantsScreen(@NonNull Collection collection) {
        getRouter().showRestaurantsScreen(collection);
    }
}