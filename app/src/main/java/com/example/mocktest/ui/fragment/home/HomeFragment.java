package com.example.mocktest.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.mocktest.R;
import com.example.mocktest.databinding.FragmentHomeBinding;
import com.example.mocktest.model.RandomModel;
import com.example.mocktest.ui.activity.MainActivity;
import static com.example.mocktest.utils.Constant.FROM_TYPE;
import static com.example.mocktest.utils.Constant.NAME_CHARTS;
import static com.example.mocktest.utils.Constant.P_TYPE;
import static com.example.mocktest.utils.Constant.Q_TYPE;
import static com.example.mocktest.utils.Constant.R_TYPE;
import static com.example.mocktest.utils.StaticMethods.isNetworkAvailable;
import static com.example.mocktest.utils.StaticMethods.rotateView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        handleClicks();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        getData();
    }

    private void handleClicks() {

        binding.btnRefresh.setOnClickListener(this::checkInternet);
        binding.pbP.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString(FROM_TYPE, P_TYPE);
            bundle.putString(NAME_CHARTS, getString(R.string.rsrp));
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_chartsFragment, bundle);
        });
        binding.pbQ.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString(NAME_CHARTS, getString(R.string.rsrq));
            bundle.putString(FROM_TYPE, Q_TYPE);
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_chartsFragment, bundle);
        });
        binding.pbR.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString(NAME_CHARTS, getString(R.string.snr));
            bundle.putString(FROM_TYPE, R_TYPE);
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_chartsFragment, bundle);
        });
    }
    private void getData() {

        if (isNetworkAvailable(getActivity())) {
            mainActivity.getRandom().observe(getViewLifecycleOwner(), this::setValues);
            binding.liNoInternet.setVisibility(View.GONE);
        } else {
            binding.liNoInternet.setVisibility(View.VISIBLE);

        }
    }
    private void checkInternet(View view) {
        rotateView(view);
        if (isNetworkAvailable(getContext())) {
            binding.liNoInternet.setVisibility(View.GONE);
            getData();
        } else {
            binding.liNoInternet.setVisibility(View.VISIBLE);
        }

    }
    private void setValues(RandomModel randomModel) {

        if (randomModel.getThrowable() == null) {

            int rsrP= randomModel.getRSRP();
            int rsrQ = randomModel.getRSRQ();
            int sinR = randomModel.getSINR();

            // set value into textView on progress
            binding.tvP.setText(String.valueOf(rsrP));
            binding.tvQ.setText(String.valueOf(rsrQ));
            binding.tvR.setText(String.valueOf(sinR));

            // check value to color the progress
            homeViewModel.checkRsrp(binding, rsrP, getResources());
            homeViewModel.checkRsrq(binding, rsrQ, getResources());
            homeViewModel.checkSnir(binding, sinR, getResources());
        } else {
            binding.liNoInternet.setVisibility(View.VISIBLE);
        }
    }

}