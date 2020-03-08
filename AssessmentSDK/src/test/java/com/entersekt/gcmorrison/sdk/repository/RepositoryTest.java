package com.entersekt.gcmorrison.sdk.repository;

import com.entersekt.gcmorrison.sdk.RandomTestData;
import com.entersekt.gcmorrison.sdk.api.EntersektApi;
import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.cache.SdkCache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryTest implements RandomTestData {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private EntersektApi mockApi;

    @Mock
    private SdkCache mockCache;

    private Repository classUnderTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        classUnderTest = new Repository(mockApi, mockCache);
    }

    @Test
    public void getAllCities() {
        // GIVEN
        when(mockApi.getCities()).thenReturn(Single.just(Collections.singletonList(randomCity())));

        // WHEN
        classUnderTest.getCities();

        // THEN
        verify(mockApi, times(1)).getCities();
    }

    @Test
    public void cacheDataOnSuccess() {
        // GIVEN
        List<City> cities = Arrays.asList(randomCity(), randomCity());
        when(mockApi.getCities()).thenReturn(Single.just(cities));

        // WHEN
        classUnderTest.getCities().blockingGet();

        // THEN
        verify(mockCache, times(1)).store(cities);
    }

    @Test
    public void returnCachedDataOnFailure() {
        // GIVEN
        List<City> cities = Arrays.asList(randomCity(), randomCity());
        when(mockCache.get()).thenReturn(cities);
        when(mockApi.getCities()).thenReturn(Single.error(new Throwable()));

        // WHEN
        List<City> results = classUnderTest.getCities().blockingGet();

        // THEN
        verify(mockCache, never()).store(cities);
        verify(mockCache, times(1)).get();
        assertArrayEquals(cities.toArray(), results.toArray());
    }
}
