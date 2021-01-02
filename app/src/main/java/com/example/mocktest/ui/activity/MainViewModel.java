package com.example.mocktest.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.mocktest.model.RandomModel;
import java.util.concurrent.TimeUnit;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.mocktest.network.RetrofitClass.getNetworkInstance;
import static com.example.mocktest.utils.StaticMethods.isNetworkAvailable;

public class MainViewModel extends ViewModel {

    MutableLiveData<RandomModel> data = new MutableLiveData<>();
    @SuppressLint("CheckResult")
    public LiveData<RandomModel> getRandom(
    ) {

        Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(tick -> getNetworkInstance().getRandom())
                .retry()
                .subscribe(randomObservable -> randomObservable.subscribe(randomModel -> {

                    data.postValue( randomModel);
                }));

        return data;

    }



}
