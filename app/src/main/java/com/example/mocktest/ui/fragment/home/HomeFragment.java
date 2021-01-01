package com.example.mocktest.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.mocktest.R;
import com.example.mocktest.databinding.FragmentHomeBinding;
import com.example.mocktest.model.RandomModel;
import com.example.mocktest.ui.activity.MainActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        getData();
    }

    private void getData() {
        mainActivity.getRandom().observe(getViewLifecycleOwner(), this::setValues);
    }

    private void setValues(RandomModel randomModel) {

        int rsrp = randomModel.getRSRP();
        int rsrq = randomModel.getRSRQ();
        int sinr = randomModel.getSINR();

        // set value into textView on progress
        binding.tvP.setText(String.valueOf(rsrp));
        binding.tvQ.setText(String.valueOf(rsrq));
        binding.tvR.setText(String.valueOf(sinr));


        // check value to color the progress
        homeViewModel.checkRsrp(binding ,rsrp,getResources());
        homeViewModel.checkRsrq(binding,rsrq, getResources());
        homeViewModel.checkSnir(binding,sinr,getResources());

    }


}