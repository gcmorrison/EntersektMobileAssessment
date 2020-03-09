package com.entersekt.gcmorrison.assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * A generic fragment used to display failures and provides the ability to retry
 */
public class FailureFragment extends Fragment {
    private final Throwable throwable;

    public FailureFragment(Throwable throwable) {
        this.throwable = throwable;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View failureView = inflater.inflate(R.layout.fragment_error, null);

        TextView errorText = failureView.findViewById(R.id.txt_error);
        errorText.setText(getString(R.string.error, throwable.getMessage()));

        failureView.findViewById(R.id.btn_retry).setOnClickListener(l -> ((MainActivity) Objects.requireNonNull(getActivity())).loadAndDisplay());

        return failureView;
    }
}
