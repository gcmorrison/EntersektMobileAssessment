package com.entersekt.gcmorrison.assessment;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.entersekt.gcmorrison.sdk.EntersektSDK;
import com.entersekt.gcmorrison.sdk.api.model.City;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCitiesAndDisplay();
    }

    private void getCitiesAndDisplay() {
        EntersektSDK.getInstance(this)
                .getAllCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    compositeDisposable.add(disposable);
                    ProgressBar progressBar = findViewById(R.id.progress_bar);
                    if (progressBar != null) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .doOnTerminate(() -> {
                    ProgressBar progressBar = findViewById(R.id.progress_bar);
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .subscribe(this::showCitiesList,
                        this::showError);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void showCitiesList(List<City> cities) {
        replaceFragment(new CitiesListFragment(cities));
    }

    private void showError(Throwable throwable) {
        // TODO
        replaceFragment(new FailureFragment());
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void onCityClicked(City city) {
        // TODO
        addFragment(new FailureFragment());
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(fragment.toString()).commit();
    }
}
