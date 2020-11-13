package com.af.upworktasks.ui.fragments.CurrencyRate;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.FragmentCurrencyRateBinding;
import com.af.upworktasks.utils.Constant;
import org.jetbrains.annotations.NotNull;
import java.util.Map;


public class CurrencyRateFragment extends Fragment {

    FragmentCurrencyRateBinding currencyRateBinding;
    private CurrencyRateViewModel currencyRateViewModel;
    private static final String TAG = "CurrencyRateFragment";
    private String local;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currencyRateViewModel = new ViewModelProvider(this).get(CurrencyRateViewModel.class);
        Log.d("GetLocal", "onCreate: " + Constant.getLocal(requireContext()));
        local = Constant.getLocal(requireContext());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        currencyRateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency_rate, container,false);
        return currencyRateBinding.getRoot();
    }

    //select rate //
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currencyRateBinding.btnEnterCrr.setOnClickListener(view1 -> {
            if (!currencyRateBinding.etCrr.getText().toString().isEmpty()){
                currencyRateViewModel.getCurrencyRateViewModel(currencyRateBinding.etCrr.getText().toString()).observe(requireActivity(), currencyRateDto -> {
                    Log.d(TAG, "onViewCreated: "  + currencyRateDto.getRate());
                    currencyRateBinding.tvRate.setText(Constant.getLocal(getContext()) + " "+displayMap(currencyRateDto.getRate()));
                });
            }else {
                Toast.makeText(getContext(), "Enter Currency Type", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        currencyRateViewModel.clear();
    }

    public String displayMap(Map<String, Object> myMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> stringObjectEntry : myMap.entrySet()) {
            Log.d(TAG, "displayMap: " + "key is: " + ((Map.Entry) stringObjectEntry).getKey() + " & Value is: " + stringObjectEntry.getValue());
            stringBuilder.append(stringObjectEntry.getValue());
        }
        return stringBuilder.toString();
    }


}