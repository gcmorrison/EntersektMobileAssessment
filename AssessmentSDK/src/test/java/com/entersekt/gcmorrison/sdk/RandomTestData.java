package com.entersekt.gcmorrison.sdk;

import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.api.model.Mall;
import com.entersekt.gcmorrison.sdk.api.model.Shop;

import java.util.UUID;

/**
 * A helper interface that generates random data for testing purposes
 */
public interface RandomTestData {
    default int randomInt() {
        return (int) (Math.random() * 100000);
    }

    default String randomString() {
        return UUID.randomUUID().toString();
    }

    default City randomCity(Mall... malls) {
        return new City.Builder().setId(randomInt())
                .setName(randomString())
                .addMalls(malls)
                .build();
    }

    default Mall randomMall(Shop... shops) {
        return new Mall.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addShops(shops)
                .build();
    }

    default Shop randomShop() {
        return new Shop.Builder()
                .setId(randomInt())
                .setName(randomString())
                .build();
    }
}
