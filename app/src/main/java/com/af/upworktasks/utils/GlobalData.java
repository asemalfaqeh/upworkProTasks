package com.af.upworktasks.utils;

import android.util.Log;
import android.widget.Toast;

import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.model.ItemState;

import java.util.ArrayList;

public class GlobalData {

    public static int currentItemSelect = -1;
    public static int selectSpinner = 0;
    public static int selectCity = 0;

    public static void updateArray(DoctorItem itemState, ArrayList<DoctorItem> doctorItems){
                      if (!doctorItems.contains(itemState)) {
                           doctorItems.add(itemState);
                          Log.d("TAG", "updateArray: ");
                     } else {
                          Log.d("TAG", "updateArray: " + " added");
                      }
    }

 /*
    Created by Asem Alfaqeh
    Skype : asem_alfaqeh123
     */
}
