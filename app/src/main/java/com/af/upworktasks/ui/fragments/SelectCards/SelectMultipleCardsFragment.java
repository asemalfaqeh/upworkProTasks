package com.af.upworktasks.ui.fragments.SelectCards;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.FragmentSelectMultipleCardsBinding;
import com.af.upworktasks.databinding.FragmentSelectSingleCardBinding;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.model.ItemState;
import com.af.upworktasks.ui.activities.BlankActivity;
import com.af.upworktasks.ui.activities.MainActivity;
import com.af.upworktasks.ui.fragments.SelectCards.adapter.SelectMultipleCardsAdapter;
import com.af.upworktasks.ui.fragments.SelectCards.adapter.SelectSingleCardAdapter;
import com.af.upworktasks.utils.GlobalData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SelectMultipleCardsFragment extends Fragment {

    private static final String TAG = "SelectSingleCardFragmen";
    FragmentSelectMultipleCardsBinding selectMultipleCardsBinding;
    private SelectMultipleCardsAdapter selectMultipleCardsAdapter;


    public SelectMultipleCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateDatalllll: " + MainActivity.doctorItemArrayList.size());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectMultipleCardsBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_select_multiple_cards, container, false);
        return selectMultipleCardsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            SelectCardsViewModel selectCardsViewModel = new ViewModelProvider(this).get(SelectCardsViewModel.class);
            selectCardsViewModel.getDoctorItemsViewModel().observe(getActivity(), doctorItems -> initRvData((ArrayList<DoctorItem>) doctorItems));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initRvData(ArrayList<DoctorItem> doctorItems){

        selectMultipleCardsAdapter = new SelectMultipleCardsAdapter(doctorItems, (cardView, position, doctorItem) -> {
                Log.d(TAG, "initRvData: " + doctorItem.getDoctorName());
                GlobalData.updateArray(doctorItem, MainActivity.doctorItemArrayList);
                cardView.setCardBackgroundColor(Color.GRAY);
            });

            selectMultipleCardsBinding.rvMultiSelectItems.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            selectMultipleCardsBinding.rvMultiSelectItems.setAdapter(selectMultipleCardsAdapter);

    }


 /*
    Created by Asem Alfaqeh
    Skype : asem_alfaqeh123

     */
}