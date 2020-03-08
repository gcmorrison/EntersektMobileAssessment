package com.entersekt.gcmorrison.sdk.repository;

import com.entersekt.gcmorrison.sdk.api.EntersektApi;
import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.cache.SdkCache;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * This class handles all the access of data for the SDK. It dictates when the SDK should hit the
 * API, and when it should pull from cache (if applicable).
 */
public class Repository {
    private final EntersektApi api;
    private final SdkCache cache;

    public Repository(EntersektApi api, SdkCache cache) {
        this.api = api;
        this.cache = cache;
    }

    /**
     * Get a list of all the cities on the Entersekt servers
     *
     * @return All the cities on the server, in the order that it was received
     */
    public Single<List<City>> getCities() {
        return api.getCities()
                .doOnSuccess(cache::store)
                .onErrorReturn(throwable -> {
                    List<City> cachedCities = cache.get();
                    // TODO: Only return the cached data if the phone is offline
                    if (cachedCities == null) {
                        throw throwable;
                    }
                    return cachedCities;
                });
    }
}
