package com.example.mocktest.ui.fragment.charts;

import com.anychart.APIlib;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.data.Set;
import com.example.mocktest.databinding.ChartsFragmentBinding;
import com.example.mocktest.model.CustomDataEntry;
import com.example.mocktest.model.RandomModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class ChartsViewModel extends ViewModel {


    private ChartsFragmentBinding binding;
    private Set set;
    private List<DataEntry> seriesData = new ArrayList<>();


    public void setBinding(ChartsFragmentBinding binding, Set set){
        this.binding =binding;
        this.set = set;
    }

    public void setRCharts(RandomModel randomModel) {

        int rsrR_p= 20 ;
        int rsrR_s1 =5 ;
        int rsrR_s2  = -5;

        if (randomModel.getSINR()>=20){
            rsrR_p = randomModel.getSINR();
        }else if (randomModel.getSINR()>=5){

            rsrR_s1 = randomModel.getSINR();
        }else if (randomModel.getSINR()>=-5){

            rsrR_s2 = randomModel.getSINR();
        }

        APIlib.getInstance().setActiveAnyChartView(binding.anyChart);
        seriesData.add(new CustomDataEntry(randomModel.getTime(), rsrR_p, rsrR_s1, rsrR_s2));
        set.data(seriesData);

    }
    public void setQCharts(RandomModel randomModel) {

        int rsrq_p= -10 ;
        int rsrq_s1 = -20 ;
        int rsrq_s2  = -30;

        if (randomModel.getRSRQ()>=-10){
            rsrq_p = randomModel.getRSRQ();
        }else if (randomModel.getRSRQ()>=-20){

            rsrq_s1 = randomModel.getRSRQ();
        }else if (randomModel.getRSRQ()>=-30){

            rsrq_s2 = randomModel.getRSRQ();
        }

        APIlib.getInstance().setActiveAnyChartView(binding.anyChart);
        seriesData.add(new CustomDataEntry(randomModel.getTime(), rsrq_p, rsrq_s1, rsrq_s2));
        set.data(seriesData);

    }
    public void setPCharts(RandomModel randomModel) {

        int rsrp_p= -60 ;
        int rsrp_s1 = -90 ;
        int rsrp_s2  = -120;

        if (randomModel.getRSRP()>=-80){
            rsrp_p = randomModel.getRSRP();
        }else if (randomModel.getRSRP()>=-100){

            rsrp_s1 = randomModel.getRSRP();
        }else if (randomModel.getRSRP()>=-200){

            rsrp_s2 = randomModel.getRSRP();
        }

        APIlib.getInstance().setActiveAnyChartView(binding.anyChart);
        seriesData.add(new CustomDataEntry(randomModel.getTime(), rsrp_p, rsrp_s1, rsrp_s2));
        set.data(seriesData);

    }
}