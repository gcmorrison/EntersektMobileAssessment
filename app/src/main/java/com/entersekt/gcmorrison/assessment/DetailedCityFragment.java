package com.entersekt.gcmorrison.assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.entersekt.gcmorrison.sdk.EntersektSDK;
import com.entersekt.gcmorrison.sdk.api.model.City;

/**
 * A detailed city fragment that displays both malls and all shops
 */
public class DetailedCityFragment extends Fragment {
    private final City city;

    public DetailedCityFragment(City city) {
        this.city = city;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.fragment_detailed, container, false);
        return inflated;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_malls_container,
                        new MallsListFragment(city.getMalls(), getString(R.string.malls_for, city.getName())))
                .commit();

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_shops_container,
                        new ShopsListFragment(EntersektSDK.getInstance(getActivity()).getShopsForCity(city), getString(R.string.shops_in, city.getName())))
                .commit();
    }
}
