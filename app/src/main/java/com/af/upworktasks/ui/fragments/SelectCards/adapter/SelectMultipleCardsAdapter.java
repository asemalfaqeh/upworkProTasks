package com.af.upworktasks.ui.fragments.SelectCards.adapter;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.SelectSingleCardAdapterBinding;
import com.af.upworktasks.interfaces.SelectItemListener;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.model.ItemState;
import com.af.upworktasks.ui.activities.MainActivity;
import com.af.upworktasks.ui.fragments.SelectCards.SelectMultipleCardsFragment;
import com.af.upworktasks.utils.GlobalData;

import java.util.ArrayList;

public class SelectMultipleCardsAdapter extends RecyclerView.Adapter<SelectMultipleCardsAdapter.ViewHolder> {
    /*
       Created by Asem Alfaqeh
       Skype : asem_alfaqeh123
        */
    private final ArrayList<DoctorItem> doctorItemArrayList;
    private final SelectItemListener selectItemListener;
    private static final String TAG = "SelectMultipleCardsAdap";

    public SelectMultipleCardsAdapter(ArrayList<DoctorItem> doctorItemArrayList, SelectItemListener selectItemListener) {
        this.doctorItemArrayList = doctorItemArrayList;
        this.selectItemListener = selectItemListener;

        for (int i = 0;i < MainActivity.doctorItemArrayList.size(); i ++){
            Log.d(TAG, "SelectMultipleCardsAdapter: " + MainActivity.doctorItemArrayList.get(i).getDoctorName());
        }

        setHasStableIds(true);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectSingleCardAdapterBinding selectSingleCardAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.select_single_card_adapter, parent, false);
        return new ViewHolder(selectSingleCardAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(doctorItemArrayList.get(position));
        holder.itemView.setOnClickListener(view -> selectItemListener.onSelectItemListener(holder.selectSingleCardAdapterBinding.cvItem ,position, doctorItemArrayList.get(position)));

            for (int i = 0; i < MainActivity.doctorItemArrayList.size() ; i ++){
                    if (MainActivity.doctorItemArrayList.get(i).getDoctorName().equals(doctorItemArrayList.get(position).getDoctorName())){
                        holder.selectSingleCardAdapterBinding.cvItem.setCardBackgroundColor(Color.GRAY);
                    }
            }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return doctorItemArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SelectSingleCardAdapterBinding selectSingleCardAdapterBinding;
        public ViewHolder(@NonNull SelectSingleCardAdapterBinding itemView) {
            super(itemView.getRoot());
            this.selectSingleCardAdapterBinding = itemView;
        }
        void bindData(DoctorItem doctorItem){
            selectSingleCardAdapterBinding.doctorEmail.setText(doctorItem.getDoctorEmail());
            selectSingleCardAdapterBinding.doctorName.setText(doctorItem.getDoctorName());
            selectSingleCardAdapterBinding.doctorStatus.setText(doctorItem.getDoctorStatus());
        }
    }
 /*
    Created by Asem Alfaqeh
    Skype : asem_alfaqeh123
     */

}
