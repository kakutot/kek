package com.example.tupkalenko.trainee.project.domain.navigation;

public interface CollectionsScreenRouter extends Router {

    void showCollectionsScreen();

    void showRestaurantsScreen(int collectionId);
}