package com.af.upworktasks.ui.fragments.SelectCards;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.FragmentSelectSingleCardBinding;
import com.af.upworktasks.interfaces.SelectItemListener;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.ui.fragments.SelectCards.adapter.SelectSingleCardAdapter;
import com.af.upworktasks.utils.GlobalData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SelectSingleCardFragments extends Fragment {


    private static final String TAG = "SelectSingleCardFragmen";
    FragmentSelectSingleCardBinding selectSingleCardBinding;
    private SelectSingleCardAdapter selectSingleCardAdapter;
    public static int positionI = -1;

    public SelectSingleCardFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectSingleCardBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_select_single_card, container, false);
        return selectSingleCardBinding.getRoot();
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
        if (doctorItems.size() != 0) {
            selectSingleCardAdapter = new SelectSingleCardAdapter(doctorItems, (cardView, position, doctorItem) -> {
                Log.d(TAG, "initRvData: " + doctorItem.getDoctorName());
                GlobalData.currentItemSelect = position;
                selectSingleCardAdapter.notifyDataSetChanged();
            });
            selectSingleCardBinding.rvSelectItems.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            selectSingleCardBinding.rvSelectItems.setAdapter(selectSingleCardAdapter);
        }

    }

}