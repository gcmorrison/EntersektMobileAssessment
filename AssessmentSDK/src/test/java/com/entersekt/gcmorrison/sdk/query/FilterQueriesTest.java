package com.entersekt.gcmorrison.sdk.query;

import com.entersekt.gcmorrison.sdk.RandomTestData;
import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.api.model.Mall;
import com.entersekt.gcmorrison.sdk.api.model.Shop;
import com.entersekt.gcmorrison.sdk.repository.Repository;

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
import java.util.NoSuchElementException;

import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class FilterQueriesTest implements RandomTestData {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Repository mockRepo;
    private FilterQueries classUnderTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        classUnderTest = new FilterQueries() {
            @Override
            public Single<List<City>> getAllCities() {
                return mockRepo.getCities();
            }
        };
    }

    @Test
    public void getSpecificCityWhenItExists() {
        // GIVEN
        City expectedCity = randomCity();
        when(mockRepo.getCities()).thenReturn(Single.just(Arrays.asList(randomCity(), expectedCity, randomCity())));

        // WHEN
        City result = classUnderTest.getCity(expectedCity.getName()).blockingGet();

        // THEN
        assertEquals(expectedCity, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void getSpecificCityWhenItDoesntExist() {
        // GIVEN
        String cityName = "I don't exist";
        when(mockRepo.getCities()).thenReturn(Single.just(Arrays.asList(randomCity(), randomCity())));

        // WHEN
        City result = classUnderTest.getCity(cityName).blockingGet();

        // THEN
        fail("Exception should have occurred");
    }

    @Test
    public void getMallsOfCityWhenThereAre() {
        // GIVEN
        List<Mall> expectedMalls = Arrays.asList(randomMall(), randomMall());
        City city = randomCity(expectedMalls.toArray(new Mall[0]));
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Mall> malls = classUnderTest.getMallsForCity(city.getName()).blockingGet();

        // THEN
        assertArrayEquals(expectedMalls.toArray(), malls.toArray());
    }

    @Test
    public void getMallsOfCityWhenThereAreNone() {
        // GIVEN
        City city = randomCity();
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Mall> malls = classUnderTest.getMallsForCity(city.getName()).blockingGet();

        // THEN
        assertTrue(malls.isEmpty());
    }

    @Test
    public void getSpecificMallInCityWhenItExists() {
        // GIVEN
        Mall expectedMall = randomMall();
        City city = randomCity(randomMall(), expectedMall, randomMall());
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        Mall result = classUnderTest.getMallInCity(city.getName(), expectedMall.getName()).blockingGet();

        // THEN
        assertEquals(expectedMall, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void getSpecificMallInCityWhenItDoesntExists() {
        // GIVEN
        String mallName = "There isn't one";
        City city = randomCity(randomMall(), randomMall());
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        Mall result = classUnderTest.getMallInCity(city.getName(), mallName).blockingGet();

        // THEN
        fail("Exception should have occurred");
    }

    @Test
    public void getShopsOfMallWhenThereAre() {
        // GIVEN
        List<Shop> expectedShops = Arrays.asList(randomShop(), randomShop());
        Mall mall = randomMall(expectedShops.toArray(new Shop[0]));
        City city = randomCity(mall);
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Shop> shops = classUnderTest.getShopsForMall(city.getName(), mall.getName()).blockingGet();

        // THEN
        assertArrayEquals(expectedShops.toArray(), shops.toArray());
    }

    @Test
    public void getShopsOfMallWhenThereAreNone() {
        // GIVEN
        Mall mall = randomMall();
        City city = randomCity(mall);
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Shop> shops = classUnderTest.getShopsForMall(city.getName(), mall.getName()).blockingGet();

        // THEN
        assertTrue(shops.isEmpty());
    }

    @Test
    public void getSpecificShopInMallWhenItExists() {
        // GIVEN
        Shop expectedShop = randomShop();
        Mall mall = randomMall(randomShop(), expectedShop, randomShop());
        City city = randomCity(mall);
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        Shop result = classUnderTest.getShopInMall(city.getName(), mall.getName(), expectedShop.getName()).blockingGet();

        // THEN
        assertEquals(expectedShop, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void getSpecificShopInMallWhenItDoesntExists() {
        // GIVEN
        String shopName = "There isn't one";
        Mall mall = randomMall(randomShop(), randomShop());
        City city = randomCity(mall);
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        Shop result = classUnderTest.getShopInMall(city.getName(), mall.getName(), shopName).blockingGet();

        // THEN
        fail("Exception should have occurred");
    }

    @Test
    public void getShopsForCityAllUnique() {
        // GIVEN
        Shop[] allShops = new Shop[]{randomShop(), randomShop(), randomShop(), randomShop()};
        City city = randomCity(randomMall(allShops[0], allShops[1]), randomMall(allShops[2], allShops[3]));
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Shop> results = classUnderTest.getShopsForCity(city.getName()).blockingGet();

        // THEN
        assertArrayEquals(allShops, results.toArray());
    }

    @Test
    public void getShopsForCityWithDuplicates() {
        // GIVEN
        String duplicateShopName = randomString();
        Shop[] allShops = new Shop[]{randomShop(duplicateShopName), randomShop(), randomShop(duplicateShopName), randomShop()};
        City city = randomCity(randomMall(allShops[0], allShops[1]), randomMall(allShops[2], allShops[3]));
        when(mockRepo.getCities()).thenReturn(Single.just(Collections.singletonList(city)));

        // WHEN
        List<Shop> results = classUnderTest.getShopsForCity(city.getName()).blockingGet();

        // THEN
        assertArrayEquals(new Shop[]{allShops[0], allShops[1], allShops[3]}, results.toArray());
    }
}