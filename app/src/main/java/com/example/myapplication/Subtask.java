package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Subtask implements Parcelable {
    private String subtaskName;

    public Subtask(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    protected Subtask(Parcel in) {
        subtaskName = in.readString();
    }

    public static final Creator<Subtask> CREATOR = new Creator<Subtask>() {
        @Override
        public Subtask createFromParcel(Parcel in) {
            return new Subtask(in);
        }

        @Override
        public Subtask[] newArray(int size) {
            return new Subtask[size];
        }
    };

    public String getSubtaskName() {
        return subtaskName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(subtaskName);
    }
}
