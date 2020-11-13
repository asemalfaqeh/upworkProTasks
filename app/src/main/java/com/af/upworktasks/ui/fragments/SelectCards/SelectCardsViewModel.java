package com.af.upworktasks.ui.fragments.SelectCards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.af.upworktasks.model.DoctorItem;

import java.util.ArrayList;
import java.util.List;

public class SelectCardsViewModel extends ViewModel {

    private final MutableLiveData<List<DoctorItem>> doctorItemMutableLiveData = new MutableLiveData<>();

    public LiveData<List<DoctorItem>> getDoctorItemsViewModel(){
         return getDummyData();
    }

    private MutableLiveData<List<DoctorItem>> getDummyData(){

        ArrayList<DoctorItem> doctorItemArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i ++){
            DoctorItem doctorItem = new DoctorItem();
            doctorItem.setDoctorEmail("Doctor Email " + i);
            doctorItem.setDoctorName("Doctor Name " + i);
            doctorItem.setDoctorStatus("Doctor Status " + i);
            doctorItemArrayList.add(doctorItem);
        }

        doctorItemMutableLiveData.postValue(doctorItemArrayList);

        return doctorItemMutableLiveData;

    }

}
