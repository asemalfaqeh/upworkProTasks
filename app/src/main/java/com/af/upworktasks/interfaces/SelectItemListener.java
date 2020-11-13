package com.af.upworktasks.interfaces;

import androidx.cardview.widget.CardView;

import com.af.upworktasks.model.DoctorItem;

public interface SelectItemListener {
    void onSelectItemListener(CardView cardView, int position, DoctorItem doctorItem);
}
