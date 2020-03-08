package com.entersekt.gcmorrison.sdk.api.model;


import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class City {
    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    @NonNull
    private final String name;

    @SerializedName("malls")
    @NonNull
    private final List<Mall> malls;

    private City(int id, @NonNull String name, @NonNull List<Mall> malls) {
        this.id = id;
        this.name = name;
        this.malls = malls;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public List<Mall> getMalls() {
        return malls;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", malls=" + malls +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                getName().equals(city.getName()) &&
                getMalls().equals(city.getMalls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMalls());
    }

    public static class Builder {
        private int id;
        private String name;
        private List<Mall> malls;

        public Builder() {
            malls = new ArrayList<>();
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder addMalls(Mall... malls) {
            if (malls != null) {
                for (Mall mall : malls) {
                    if (mall == null) {
                        continue;
                    }
                    this.malls.add(mall);
                }
            }

            return this;
        }

        public Builder from(City city) {
            return setId(city.id)
                    .setName(city.name)
                    .setMalls(city.malls);
        }

        private Builder setMalls(List<Mall> malls) {
            this.malls = malls;
            return this;
        }

        public City build() {
            return new City(id, name, malls);
        }
    }
}
