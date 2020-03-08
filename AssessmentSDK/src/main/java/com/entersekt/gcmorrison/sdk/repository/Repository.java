package com.entersekt.gcmorrison.sdk.repository;

import com.entersekt.gcmorrison.sdk.api.EntersektApi;
import com.entersekt.gcmorrison.sdk.api.model.City;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * This class handles all the access of data for the SDK. It dictates when the SDK should hit the
 * API, and when it should pull from cache (if applicable).
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
    public Single<List<City>> getCities() {
        return api.getCities();
    }
}
