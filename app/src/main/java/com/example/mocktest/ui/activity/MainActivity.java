package com.example.mocktest.ui.activity;


import android.os.Bundle;

import com.example.mocktest.R;
import com.example.mocktest.model.RandomModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import static com.example.mocktest.utils.StaticMethods.setDefaultLanguage;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultLanguage(this,"en");
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    public LiveData<RandomModel> getRandom() {
      return mViewModel.getRandom();
    }





}