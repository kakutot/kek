package com.example.tupkalenko.trainee.project.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class City {

    private int id;

    @Nullable
    private String name;

    private int countryId;

    @Nullable
    private String countryName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Nullable
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@NonNull String countryName) {
        this.countryName = countryName;
    }
}