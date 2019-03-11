package com.example.tupkalenko.trainee.project.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Collection {

    private int id;

    @Nullable
    private String title;

    @Nullable
    private String imageUrl;

    private int resultsCount;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }
}