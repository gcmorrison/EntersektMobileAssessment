package com.entersekt.gcmorrison.sdk.api.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Shop {
    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    @NonNull
    private final String name;

    private Shop(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return getId() == shop.getId() &&
                getName().equals(shop.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder from(Shop shop) {
            return setId(shop.id)
                    .setName(shop.name);
        }

        public Shop build() {
            return new Shop(id, name);
        }
    }
}
