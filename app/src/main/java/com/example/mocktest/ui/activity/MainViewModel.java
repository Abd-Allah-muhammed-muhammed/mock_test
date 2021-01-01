package com.example.mocktest.ui.activity;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mocktest.model.RandomModel;
import java.util.concurrent.TimeUnit;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.example.mocktest.network.RetrofitClass.getNetworkInstance;

public class MainViewModel extends ViewModel {

    MutableLiveData<RandomModel> data = new MutableLiveData<>();
    @SuppressLint("CheckResult")
    public LiveData<RandomModel> getRandom(
    ) {

        Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(tick -> getNetworkInstance().getRandom())
                .retry()
                .subscribe(jokeObservable -> jokeObservable.subscribe(randomModel -> {

                    data.postValue( randomModel);
                }));

        return data;

    }



}
