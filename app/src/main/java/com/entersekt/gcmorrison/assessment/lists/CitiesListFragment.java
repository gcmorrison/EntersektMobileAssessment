package com.entersekt.gcmorrison.assessment.lists;

import com.entersekt.gcmorrison.assessment.MainActivity;
import com.entersekt.gcmorrison.assessment.recycler.ClickableListFragment;
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
    protected String itemName(City city) {
        return city.getName();
    }

    @Override
    protected void onClick(City city) {
        ((MainActivity) Objects.requireNonNull(getActivity())).onCityClicked(city);
    }
}
