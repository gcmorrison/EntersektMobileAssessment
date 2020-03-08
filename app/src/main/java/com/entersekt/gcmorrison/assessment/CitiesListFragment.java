package com.entersekt.gcmorrison.assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.entersekt.gcmorrison.assessment.recycler.ClickableRecyclerAdapter;
import com.entersekt.gcmorrison.sdk.api.model.City;

import java.util.List;
import java.util.Objects;

public class CitiesListFragment extends Fragment {

    private final List<City> cities;

    public CitiesListFragment(List<City> cities) {
        this.cities = cities;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.fragment_city_list, null);

        RecyclerView recyclerView = (RecyclerView) inflated.getRootView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ClickableRecyclerAdapter<>(cities, City::getName, this::handleCityClicked));

        return inflated;
    }

    private void handleCityClicked(City city) {
        ((MainActivity) Objects.requireNonNull(getActivity())).onCityClicked(city);
    }
}
