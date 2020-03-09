package com.entersekt.gcmorrison.assessment;

import androidx.annotation.Nullable;

import com.entersekt.gcmorrison.sdk.api.model.Shop;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A fragment to display all shops provided to it
 */
public class ShopsListFragment extends ClickableListFragment<Shop> {
    public ShopsListFragment(List<Shop> items, @Nullable String title) {
        super(items, title);
    }

    @Override
    String itemName(Shop shop) {
        return shop.getName();
    }

    @Override
    void onClick(Shop shop) {
        Snackbar.make(getView(), "Clicked on " + shop.getName(), Snackbar.LENGTH_SHORT).show();
    }
}