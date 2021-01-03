package com.example.mocktest.ui.fragment.splash;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mocktest.R;
import com.example.mocktest.databinding.SplashFragmentBinding;

public class SplashFragment extends Fragment {

    private SplashFragmentBinding binding ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.splash_fragment, container, false);
        handleClicks();
        return binding.getRoot();
    }

    private void handleClicks() {
        binding.btnStart.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment);
        });
    }


}