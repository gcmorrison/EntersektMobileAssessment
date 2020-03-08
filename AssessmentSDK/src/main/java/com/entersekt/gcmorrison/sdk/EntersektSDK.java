package com.entersekt.gcmorrison.sdk;

import androidx.annotation.VisibleForTesting;

import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.api.retrofit.RetrofitEntersektApi;
import com.entersekt.gcmorrison.sdk.query.FilterQueries;
import com.entersekt.gcmorrison.sdk.repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * This is the primary interaction point between the consumer and the SDK. This class should expose
 * all the functionality that a consumer may want from this SDK, and it will also be the least
 * obfuscated of all the classes in the SDK.
 */
public class EntersektSDK implements FilterQueries {

    /**
     * There are a number of ways of implementing an SDK's interaction. The most common one is
     * probably a Singleton pattern. While this is very lightweight and easy to use both from a
     * developer (Entersekt) and a consumer (client) perspective, it can have negative side-effects
     * when the SDK has very complex resource requirements.
     * <p>
     * For the purposes of this technical assessment, the Singleton pattern was followed, but for
     * complex SDK's I might rather opt for a BoundService approach.
     */
    private static EntersektSDK instance;
    private final Repository repository;

    /**
     * Constructor made package-private so it can be used for testing purposes, and to bypass some
     * Singleton constraints.
     * <p>
     * This can be done better with dependency injection like Dagger, and then overriding it in the
     * tests. But to save some time this was not followed
     *
     * @param repository Repository instance to use during testing
     */
    @VisibleForTesting
    EntersektSDK(Repository repository) {
        this.repository = repository;
    }

    public static EntersektSDK getInstance() {
        if (instance == null) {
            instance = new EntersektSDK(new Repository(new RetrofitEntersektApi()));
        }
        return instance;
    }

    @Override
    public Single<List<City>> getAllCities() {
        return repository.getCities();
    }
}
