package com.entersekt.gcmorrison.assessment;

import com.entersekt.gcmorrison.sdk.api.model.City;

import java.util.List;
import java.util.Objects;

/**
 * A fragment to display all cities provided to it
 */
public class CitiesListFragment extends ClickableListFragment<City> {
    public CitiesListFragment(List<City> items) {
        super(items);
    }

    @Override
    String itemName(City city) {
        return city.getName();
    }

    @Override
    void onClick(City city) {
        ((MainActivity) Objects.requireNonNull(getActivity())).onCityClicked(city);
    }
}
