package com.af.upworktasks.ui.fragments.CurrencyRate;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.af.upworktasks.datasource.ApiService;
import com.af.upworktasks.datasource.RetrofitInstance;
import com.af.upworktasks.model.CurrencyRate;
import com.af.upworktasks.model.CurrencyRateDto;
import com.af.upworktasks.utils.Constant;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CurrencyRateViewModel extends ViewModel {

    private final MutableLiveData<CurrencyRateDto> currencyRateDtoMutableLiveData = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "CurrencyRateViewModel";

    public LiveData<CurrencyRateDto> getCurrencyRateViewModel(String local){

        CurrencyRateDto currencyRateDto1 = new CurrencyRateDto();
        ApiService apiService = RetrofitInstance.getInstance().getRetrofitSecureInstance();
        Observable<CurrencyRate> currencyRateDtoObservable = apiService.getCurrencyRate(Constant.API_FREAKS_KEY, local);
        compositeDisposable.add(currencyRateDtoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CurrencyRate>() {
                    @Override
                    public void onNext(@NonNull CurrencyRate currencyRateDto) {

                        Log.d(TAG, "onNext: " + currencyRateDto.getBase() + " rate " + currencyRateDto.getRates().toString() + "  " );

                        Gson gson = new Gson();

                        String json = gson.toJson(currencyRateDto.getRates());

                        try {

                            JSONObject jsonObject = new JSONObject(json);
                            Map<String, Object> map = new TreeMap<>();

                            Iterator<String> keysItr = jsonObject.keys();

                            while(keysItr.hasNext()) {
                                String key = keysItr.next();
                                Object value = jsonObject.get(key);
                                map.put(key, value);
                            }

                            currencyRateDto1.setRate(map);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        currencyRateDtoMutableLiveData.postValue(currencyRateDto1);
                    }
                }));

                return currencyRateDtoMutableLiveData;
    }

    public void clear(){
        compositeDisposable.clear();
    }

}
