package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Parcelable {
    private String name;
    private List<Subtask> subtasks;

    public Task(String name) {
        this.name = name;
        this.subtasks = new ArrayList<>();
    }

    protected Task(Parcel in) {
        name = in.readString();
        subtasks = in.createTypedArrayList(Subtask.CREATOR);
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getName() {
        return name;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeTypedList(subtasks);
    }
}
