package com.af.upworktasks.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.BlankActivityBinding;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.settings.FragmentUtils;
import com.af.upworktasks.ui.fragments.CurrencyRate.CurrencyRateFragment;
import com.af.upworktasks.ui.fragments.SelectCards.SelectMultipleCardsFragment;
import com.af.upworktasks.ui.fragments.SelectCards.SelectSingleCardFragments;
import com.af.upworktasks.ui.fragments.SelectCountries.SelectCountryFragment;
import com.af.upworktasks.ui.fragments.YoutubePlayer.YoutubePlayerFragment;

import java.util.ArrayList;

public class BlankActivity extends AppCompatActivity {

    BlankActivityBinding blankActivityBinding;
    private static final String TAG = "BlankActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        blankActivityBinding = DataBindingUtil.setContentView(this, R.layout.blank_activity);
            String from = getIntent().getExtras().getString("from");

            switch (from) {
                case "SelectSingleCardFragments":
                    FragmentUtils.replaceFragmentUtil(getSupportFragmentManager(), new SelectSingleCardFragments(), R.id.frame_content_layout);
                    break;
                case "SelectMultipleCardsFragment":
                    FragmentUtils.replaceFragmentUtil(getSupportFragmentManager(), new SelectMultipleCardsFragment(), R.id.frame_content_layout);
                    break;
                case "YoutubePlayerFragment":
                    FragmentUtils.replaceFragmentUtil(getSupportFragmentManager(), new YoutubePlayerFragment(), R.id.frame_content_layout);
                    break;
                case "CurrencyRateFragment" :
                    FragmentUtils.replaceFragmentUtil(getSupportFragmentManager(), new CurrencyRateFragment(), R.id.frame_content_layout);
                    break;
                case "SelectCountryFragment" :
                     FragmentUtils.replaceFragmentUtil(getSupportFragmentManager(), new SelectCountryFragment(), R.id.frame_content_layout);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}