package com.entersekt.gcmorrison.sdk.repository;

import com.entersekt.gcmorrison.sdk.RandomTestData;
import com.entersekt.gcmorrison.sdk.api.EntersektApi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepositoryTest implements RandomTestData {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private EntersektApi mockApi;

    private Repository classUnderTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        classUnderTest = new Repository(mockApi);
    }

    @Test
    public void getAllCities() {
        // GIVEN / WHEN
        classUnderTest.getCities();

        // THEN
        verify(mockApi, times(1)).getCities();
    }
}
