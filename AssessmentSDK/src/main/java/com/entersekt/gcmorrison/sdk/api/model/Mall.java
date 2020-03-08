package com.entersekt.gcmorrison.sdk.api.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mall {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    @NonNull
    private String name;

    @SerializedName("shops")
    @NonNull
    private List<Shop> shops;

    private Mall(int id, @NonNull String name, @NonNull List<Shop> shops) {
        this.id = id;
        this.name = name;
        this.shops = shops;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public List<Shop> getShops() {
        return shops;
    }

    @Override
    public String toString() {
        return "Mall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shops=" + shops +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mall)) return false;
        Mall mall = (Mall) o;
        return getId() == mall.getId() &&
                getName().equals(mall.getName()) &&
                getShops().equals(mall.getShops());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getShops());
    }

    public static class Builder {
        private int id;
        private String name;
        private List<Shop> shops;

        public Builder() {
            shops = new ArrayList<>();
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder addShops(Shop... shops) {
            if (shops != null) {
                for (Shop shop : shops) {
                    if (shop == null) {
                        continue;
                    }
                    this.shops.add(shop);
                }
            }
            return this;
        }

        public Builder from(Mall mall) {
            return setId(mall.id)
                    .setName(mall.name)
                    .setShops(mall.shops);
        }

        private Builder setShops(List<Shop> shops) {
            this.shops = shops;
            return this;
        }

        public Mall build() {
            return new Mall(id, name, shops);
        }
    }
}
