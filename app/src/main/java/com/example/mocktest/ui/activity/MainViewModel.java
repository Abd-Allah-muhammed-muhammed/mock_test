package com.example.mocktest.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.mocktest.model.RandomModel;
import java.util.concurrent.TimeUnit;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
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
                .subscribe(new DisposableObserver<Observable<RandomModel>>() {
                    @Override
                    public void onNext(@NotNull Observable<RandomModel> randomModelObservable) {
                        try {

                            data.postValue(randomModelObservable.blockingFirst());

                        }catch (Exception e){
                            data.postValue(new RandomModel(e));
                        }

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        data.postValue(new RandomModel(e));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;




    }



}
