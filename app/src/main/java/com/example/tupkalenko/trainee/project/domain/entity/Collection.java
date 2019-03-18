package com.example.tupkalenko.trainee.project.domain.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection)o;

        return id == that.id &&
               resultsCount == that.resultsCount &&
               Objects.equals(title, that.title) &&
               Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageUrl, resultsCount);
    }
}