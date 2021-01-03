package com.example.mocktest.ui.fragment.charts;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.mocktest.R;
import com.example.mocktest.databinding.ChartsFragmentBinding;
import com.example.mocktest.model.CustomDataEntry;
import com.example.mocktest.model.RandomModel;
import com.example.mocktest.ui.activity.MainActivity;
import com.example.mocktest.utils.StaticMethods;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.example.mocktest.utils.Constant.FROM_TYPE;
import static com.example.mocktest.utils.Constant.P_TYPE;
import static com.example.mocktest.utils.Constant.Q_TYPE;
import static com.example.mocktest.utils.Constant.R_TYPE;

public class ChartsFragment extends Fragment {

    private ChartsViewModel mViewModel;
    ChartsFragmentBinding binding;
    MainActivity mainActivity;


    private Set set;
    private List<DataEntry> seriesData = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.charts_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        handleClick();
        return binding.getRoot();
    }

    private void handleClick() {
        binding.btnBack.setOnClickListener(view -> mainActivity.onBackPressed());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        initCartesianChart();
        getDataCharts();

    }

    private void getDataCharts() {

        mainActivity.getRandom().observe(getViewLifecycleOwner(), this::getValues);
    }

    private void getValues(RandomModel randomModel) {

        randomModel.setTime(StaticMethods.GetCurrentTime());



        if (getArguments()!=null)
        {
            switch (getArguments().getString(FROM_TYPE)){

                case P_TYPE:

                    setPCharts(randomModel);
                    break;

                case Q_TYPE:

                    setQCharts(randomModel);

                    break;


                case R_TYPE:

                    setRCharts(randomModel);

                    break;

            }
        }


    }

    private void setRCharts(RandomModel randomModel) {

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

    private void setQCharts(RandomModel randomModel) {

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

    private void setPCharts(RandomModel randomModel) {

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

    private void initCartesianChart() {

        APIlib.getInstance().setActiveAnyChartView(binding.anyChart);

        List<DataEntry> seriesData = new ArrayList<>();

        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        seriesData.add(new CustomDataEntry(StaticMethods.GetCurrentTime(), -60, -100, -180));

         set = Set.instantiate();
        set.data(seriesData);

        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(getString(R.string.rsrp)+" P");
        series1.hovered().markers().enabled(true);

        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);

        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        series1.color("#1E212B");



        Line series2 = cartesian.line(series2Mapping);
        series2.name(getString(R.string.rsrp)+" S1");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);


        Line series3 = cartesian.line(series3Mapping);
        series3.name(getString(R.string.rsrp)+" S2");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);

        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        binding.anyChart.setChart(cartesian);
    }

}