package com.entersekt.gcmorrison.assessment.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.entersekt.gcmorrison.assessment.R;

import java.util.List;

/**
 * This is a generic RecyclerView adapter that will display any type of data as a simple clickable
 * name.
 */
public class ClickableRecyclerAdapter<T> extends RecyclerView.Adapter<ClickableRecyclerAdapter.ViewHolder> {
    private final Function<T, String> toName;
    private final Consumer<T> onClick;
    private List<T> data;

    /**
     * Constructor for this generic clickable adapter.
     *
     * @param data    The data to display
     * @param toName  A function to convert a data item into a name String
     * @param onClick A callback that will be triggered when an item is selected
     */
    public ClickableRecyclerAdapter(List<T> data, Function<T, String> toName, Consumer<T> onClick) {
        this.data = data;
        this.toName = toName;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View displayView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_clickable_name, parent, false);
        return new ViewHolder((TextView) displayView.getRootView());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T item = data.get(position);
        holder.displayView.setText(toName.apply(item));
        holder.displayView.setOnClickListener(l -> onClick.accept(item));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final TextView displayView;

        public ViewHolder(@NonNull TextView displayView) {
            super(displayView);
            this.displayView = displayView;
        }
    }
}
