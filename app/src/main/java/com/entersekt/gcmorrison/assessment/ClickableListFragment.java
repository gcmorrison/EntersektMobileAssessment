package com.entersekt.gcmorrison.assessment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.entersekt.gcmorrison.assessment.recycler.ClickableRecyclerAdapter;

import java.util.List;

/**
 * A generic clickable list fragment that will display all items received and provide onClick
 * functionality
 */
public abstract class ClickableListFragment<T> extends Fragment {
    private final List<T> items;
    @Nullable
    private final String title;

    public ClickableListFragment(List<T> items) {
        this(items, null);
    }

    public ClickableListFragment(List<T> items, @Nullable String title) {
        this.items = items;
        this.title = title;
    }

    /**
     * Get the preferred name of the item to be displayed in the list
     *
     * @param item The item that will be displayed
     * @return A string name
     */
    abstract String itemName(T item);

    /**
     * Handle the event when a user clicks on an item in the list
     *
     * @param item The item that was clicked
     */
    abstract void onClick(T item);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflated = inflater.inflate(getLayoutId(), null);

        setupListView(inflated.findViewById(R.id.list));
        setupTitleView(inflated);

        return inflated;
    }

    private void setupListView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ClickableRecyclerAdapter<T>(items, this::itemName, this::onClick));
    }

    private void setupTitleView(View inflated) {
        if (!TextUtils.isEmpty(title)) {
            TextView titleView = inflated.findViewById(R.id.txt_list_title);
            View divider = inflated.findViewById(R.id.divider);

            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);

            divider.setVisibility(View.VISIBLE);
        }
    }

    /**
     * The layout that will be inflated for this fragment
     */
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }
}
