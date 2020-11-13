package com.af.upworktasks.ui.fragments.SelectCards.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.af.upworktasks.R;
import com.af.upworktasks.databinding.SelectSingleCardAdapterBinding;
import com.af.upworktasks.interfaces.SelectItemListener;
import com.af.upworktasks.model.DoctorItem;
import com.af.upworktasks.ui.fragments.SelectCards.SelectSingleCardFragments;
import com.af.upworktasks.utils.GlobalData;

import java.util.ArrayList;

public class SelectSingleCardAdapter extends RecyclerView.Adapter<SelectSingleCardAdapter.ViewHolder> {

    private final ArrayList<DoctorItem> doctorItemArrayList;
    private final SelectItemListener selectItemListener;
    private static final String TAG = "SelectSingleCardAdapter";

    public SelectSingleCardAdapter(ArrayList<DoctorItem> doctorItemArrayList, SelectItemListener selectItemListener) {
        this.doctorItemArrayList = doctorItemArrayList;
        this.selectItemListener = selectItemListener;
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
        Log.d(TAG, "onBindViewHolder: ");
        if (position == GlobalData.currentItemSelect){
            holder.selectSingleCardAdapterBinding.cvItem.setCardBackgroundColor(Color.GRAY);
        }else {
            holder.selectSingleCardAdapterBinding.cvItem.setCardBackgroundColor(Color.WHITE);
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


}
