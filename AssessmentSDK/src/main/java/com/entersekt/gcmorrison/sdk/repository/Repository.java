package com.entersekt.gcmorrison.sdk.repository;

import com.entersekt.gcmorrison.sdk.api.EntersektApi;
import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.api.model.Mall;
import com.entersekt.gcmorrison.sdk.api.model.Shop;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * This class handles all the access of data for the SDK. It provides methods that a consumer can use
 * to reach specific pieces of data. But it tries not to dictate behaviour for the consumer, as the
 * SDK will not always know how the consumer will want to use its data.
 * <br><br>
 * <b>NOTE:</b> This class performs the explicit query and filtering of results. It does not have
 * support for finding specific child data in a parent object (e.g. finding a specific Mall in an
 * existing City object). This is intentional because if the consumer queries the data through this
 * SDK it implies they do not have access to the object already.
 */
public class Repository {
    private final EntersektApi api;

    public Repository(EntersektApi api) {
        this.api = api;
    }

    /**
     * Get a list of all the cities on the Entersekt servers
     *
     * @return All the cities on the server, in the order that it was received
     */
    public Single<List<City>> getAllCities() {
        return api.getCities();
    }

    /**
     * Get a specific city, if it exists, or send an error if the city doesn't exist.
     *
     * @param name The name of the city to find
     * @return The city, if found, or an appropriate error
     */
    public Single<City> getCity(String name) {
        return getAllCities()
                .toObservable()
                .flatMap(Observable::fromIterable)
                .filter(city -> city.getName().equalsIgnoreCase(name))
                .firstOrError();
    }

    /**
     * Get all the malls in a specific city
     *
     * @param cityName The specific city in question
     * @return All the malls for that city, if any
     */
    public Single<List<Mall>> getMallsForCity(String cityName) {
        return getCity(cityName)
                .map(City::getMalls);
    }

    /**
     * Get a specific mall in a specific city, if it exists
     *
     * @param cityName The specific city in question
     * @param mallName The specific mall to find
     * @return The mall, if found, or an appropriate error
     */
    public Single<Mall> getMallInCity(String cityName, String mallName) {
        return getMallsForCity(cityName)
                .toObservable()
                .flatMap(Observable::fromIterable)
                .filter(mall -> mall.getName().equalsIgnoreCase(mallName))
                .firstOrError();
    }

    /**
     * Get all the shops for a specific city and mall
     *
     * @param cityName The specific city in question
     * @param mallName The specific mall in question
     * @return All the shops for the corresponding city and mall, if any
     */
    public Single<List<Shop>> getShopsForMall(String cityName, String mallName) {
        return getMallInCity(cityName, mallName)
                .map(Mall::getShops);
    }

    /**
     * Get a specific shop in a specific city and mall, if any, or return an appropriate error
     *
     * @param cityName The specific city in question
     * @param mallName The specific mall in question
     * @param shopName The specific shop to find
     * @return The specific shop, if found, or return an appropriate error
     */
    public Single<Shop> getShopInMall(String cityName, String mallName, String shopName) {
        return getShopsForMall(cityName, mallName)
                .toObservable()
                .flatMap(Observable::fromIterable)
                .filter(shop -> shop.getName().equalsIgnoreCase(shopName))
                .firstOrError();
    }

    /**
     * Get all the shops for a specific city, across all malls
     *
     * @param cityName The specific city in quetion
     * @return All the shops in that city
     */
    public Single<List<Shop>> getShopsForCity(String cityName) {
        return getCity(cityName)
                .toObservable()
                .map(City::getMalls)
                .flatMap(Observable::fromIterable)
                .map(Mall::getShops)
                .flatMap(Observable::fromIterable)
                .toList();
    }
}
