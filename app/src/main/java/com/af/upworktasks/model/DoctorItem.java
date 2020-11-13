package com.af.upworktasks.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DoctorItem implements Parcelable {

    private String doctorName;
    private String doctorEmail;
    private String doctorStatus;

    protected DoctorItem(Parcel in) {
        doctorName = in.readString();
        doctorEmail = in.readString();
        doctorStatus = in.readString();
    }

    public DoctorItem(){}

    public static final Creator<DoctorItem> CREATOR = new Creator<DoctorItem>() {
        @Override
        public DoctorItem createFromParcel(Parcel in) {
            return new DoctorItem(in);
        }

        @Override
        public DoctorItem[] newArray(int size) {
            return new DoctorItem[size];
        }
    };

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(String doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    @Override
    public String toString() {
        return "DoctorItem{" +
                "doctorName='" + doctorName + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", doctorStatus='" + doctorStatus + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(doctorName);
        parcel.writeString(doctorEmail);
        parcel.writeString(doctorStatus);
    }
}
