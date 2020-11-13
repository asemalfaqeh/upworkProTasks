package com.af.upworktasks.ui.fragments.SelectCountries;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.FragmentSelectCountryBinding;
import com.af.upworktasks.model.Countries;
import com.af.upworktasks.utils.GlobalData;

import java.util.ArrayList;
import java.util.Locale;

public class SelectCountryFragment extends Fragment {

    /*
    Created by Asem Alfaqeh
    Skype : asem_alfaqeh123
     */

    //cities link : https://raw.githubusercontent.com/shivammathur/countrycity/master/data/geo.json

    private FragmentSelectCountryBinding selectCountryBinding;
    private SelectCountriesViewModel selectCountriesViewModel;
    private static final String TAG = "SelectCountryFragment";
    private final ArrayList<String > stringArrayList = new ArrayList<>();
    private final ArrayList<String > countryNativeName = new ArrayList<>();

    public SelectCountryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectCountriesViewModel = new ViewModelProvider(this).get(SelectCountriesViewModel.class);
        Log.d(TAG, "onCreate: " + Locale.getDefault().getDisplayCountry() + " lang: " +
                Locale.getDefault().getDisplayLanguage());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectCountryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_country, container, false);
        return selectCountryBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectCountriesViewModel.getCountries().observe(getActivity(), countries -> {
            for (int i =0; i < countries.size(); i ++){
                countryNativeName.add(countries.get(i).getEnName());
                if (Locale.getDefault().getLanguage().equals("ar")){
                    stringArrayList.add(countries.get(i).getArName());
                }else {
                    stringArrayList.add(countries.get(i).getEnName());
                }
            }


            String[] strings = new String[stringArrayList.size()];
            strings = stringArrayList.toArray(strings);

            if (this.getActivity()!= null) {

                ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, stringArrayList.toArray(strings));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                selectCountryBinding.spCountries.setAdapter(arrayAdapter);
                selectCountryBinding.spCountries.setSelection(GlobalData.selectSpinner);
                selectCountryBinding.spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        GlobalData.selectSpinner = i;
                        getCitiesByCountry(countryNativeName.get(GlobalData.selectSpinner));
                        Log.d(TAG, "onItemSelected: " + countryNativeName.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });

    }


    private void getCitiesByCountry(String enCountry){

        selectCountriesViewModel.getCities(enCountry).observe(getActivity(), cities -> {

            String[] citiesString = new String[cities.size()];
            citiesString = cities.toArray(citiesString);

            if (this.getActivity()!= null) {

                ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, cities.toArray(citiesString));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                selectCountryBinding.spCities.setAdapter(arrayAdapter);
                selectCountryBinding.spCities.setSelection(GlobalData.selectCity);
                selectCountryBinding.spCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        GlobalData.selectCity = i;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}
 /*
    Created by Asem Alfaqeh
    Skype : asem_alfaqeh123
     */