package com.entersekt.gcmorrison.assessment;

import androidx.annotation.Nullable;

import com.entersekt.gcmorrison.sdk.api.model.Mall;

import java.util.List;
import java.util.Objects;

/**
 * A fragment to display all malls provided to it
 */
public class MallsListFragment extends ClickableListFragment<Mall> {
    public MallsListFragment(List<Mall> items, @Nullable String title) {
        super(items, title);
    }

    @Override
    String itemName(Mall mall) {
        return mall.getName();
    }

    @Override
    void onClick(Mall mall) {
        ((MainActivity) Objects.requireNonNull(getActivity())).onMallClicked(mall);
    }
}