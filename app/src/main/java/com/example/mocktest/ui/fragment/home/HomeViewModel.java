package com.example.mocktest.ui.fragment.home;

import android.content.res.Resources;

import com.example.mocktest.R;
import com.example.mocktest.databinding.FragmentHomeBinding;

import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    public void checkRsrp(FragmentHomeBinding binding, int rsrp, Resources resources) {

        if (rsrp >= -60) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_max_p));

        } else if (rsrp >= -70) {
            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_60_p));

        } else if (rsrp >= -80) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_70_p));
        } else if (rsrp >= -90) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_80_p));
        } else if (rsrp >= -100) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_90_p));
        } else if (rsrp >= -110) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_100_p));
        } else if (rsrp >= -200) {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_min_p));
        } else {

            binding.pbP.setProgressDrawable(resources.getDrawable(R.drawable.bg_min_p));
        }

        binding.pbP.setProgress(rsrp);


    }
    public void checkSnir(FragmentHomeBinding binding,int sinr  , Resources resources) {


        if (sinr >= 30) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_max_r));
        } else if (sinr >= 25) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_30_r));

        } else if (sinr >= 20) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_25_r));

        } else if (sinr >= 15) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_20_r));

        } else if (sinr >= 10) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_15_r));

        } else if (sinr >= 5) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_10_r));

        } else if (sinr >= 0) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_5_r));

        }else if (sinr >= -100) {
            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_min_r));

        }else {

            binding.pbR.setProgressDrawable(resources.getDrawable(R.drawable.bg_min_r));
        }


        binding.pbR.setProgress(sinr);
    }
    public void checkRsrq(FragmentHomeBinding binding ,int rsrq, Resources resources) {

        if (rsrq >= 0) {
            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_max_q));


        } else if (rsrq >= -3) {

            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_3_q));

        } else if (rsrq >= -9) {

            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_9_q));

        } else if (rsrq >= -14) {

            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_14_q));

        } else if (rsrq >= -20) {

            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_max_q));

        } else {
            binding.pbQ.setProgressDrawable(resources.getDrawable(R.drawable.bg_max_q));
        }

        binding.pbQ.setProgress(rsrq);


    }

}