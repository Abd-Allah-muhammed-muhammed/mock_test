package com.example.mocktest.network;

import com.example.mocktest.model.RandomModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.example.mocktest.utils.Constant.RANDOM;

public interface NetworkInterface {


    @GET(RANDOM)
    Observable<RandomModel> getRandom();

}
