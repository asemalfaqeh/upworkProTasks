package com.af.upworktasks.ui.fragments.SelectCountries;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.af.upworktasks.model.Countries;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SelectCountriesViewModel extends AndroidViewModel {

    private final MutableLiveData<ArrayList<Countries>> stringMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<String>> arrayListMutableLiveData = new MutableLiveData<>();

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public SelectCountriesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public LiveData<ArrayList<Countries>> getCountries(){

        try {
            String countriesJson = AssetJSONFile("countries.json", context);
            Log.d("", "getCountries: " + " json file " + countriesJson);
            JSONObject jsonObject = new JSONObject(countriesJson);

           ArrayList<Countries> arrayList = new ArrayList<>();

            Iterator<String> keysItr = jsonObject.keys();
            while(keysItr.hasNext()) {
                Countries countries = new Countries();
                JSONObject jsonObject1 = (JSONObject) jsonObject.get(keysItr.next());
                countries.setEnName(jsonObject1.getString("en_name"));
                countries.setArName(jsonObject1.getString("native_name"));
                arrayList.add(countries);
            }

            stringMutableLiveData.postValue(arrayList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringMutableLiveData;
    }


    public LiveData<ArrayList<String>> getCities(String enCountryName){

        try {

            String citiesjson = AssetJSONFile("cities.json", context);

            JSONObject jsonObject1 = new JSONObject(citiesjson);
            ArrayList<String> citiesArrayList = new ArrayList<>();

            Map<String, Object> map = new TreeMap<>();

            Iterator<String> keysItr = jsonObject1.keys();

            while(keysItr.hasNext()) {
                map.put(keysItr.next(), "");
                for (String keySet : map.keySet()) {
                    if (keySet.equals(enCountryName)) {
                        JSONArray jsonArray = jsonObject1.getJSONArray(keySet);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            citiesArrayList.add(jsonArray.getString(i));
                        }
                    }
                }
            }

            arrayListMutableLiveData.postValue(citiesArrayList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayListMutableLiveData;
    }

    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

}
