package com.entersekt.gcmorrison.sdk;

import com.entersekt.gcmorrison.sdk.repository.Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EntersektSDKTest implements RandomTestData {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Repository mockRepo;

    private EntersektSDK classUnderTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        classUnderTest = new EntersektSDK(mockRepo);
    }

    @Test
    public void getAllCities() {
        // GIVEN / WHEN
        classUnderTest.getAllCities();

        // THEN
        verify(mockRepo, times(1)).getCities();
    }
}