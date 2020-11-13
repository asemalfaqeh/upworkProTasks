package com.af.upworktasks.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.ActivityMainBinding;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.settings.FragmentUtils;
import com.af.upworktasks.ui.fragments.SelectCards.SelectMultipleCardsFragment;
import com.af.upworktasks.ui.fragments.SelectCards.SelectSingleCardFragments;
import com.af.upworktasks.utils.Constant;
import com.af.upworktasks.utils.GlobalData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    public static ArrayList<DoctorItem> doctorItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       doctorItemArrayList = new ArrayList<>();
       activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       activityMainBinding.btnSSelectCards.setOnClickListener(view -> startBlankActivity("SelectSingleCardFragments"));
       activityMainBinding.btnMSelectCards.setOnClickListener(view -> startBlankActivity("SelectMultipleCardsFragment"));
       activityMainBinding.btnYoutubePlayer.setOnClickListener(view -> startBlankActivity("YoutubePlayerFragment"));
       activityMainBinding.btnCurrencyConverter.setOnClickListener(view -> startBlankActivity("CurrencyRateFragment"));
       activityMainBinding.btnSelectCountries.setOnClickListener(view -> startBlankActivity("SelectCountryFragment"));
    }

    private void startBlankActivity(String from){
        Intent intent = new Intent(this, BlankActivity.class);
        intent.putExtra("from", from);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doctorItemArrayList.clear();
        GlobalData.currentItemSelect = -1;
    }
}