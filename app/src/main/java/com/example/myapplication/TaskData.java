package com.example.myapplication;
import java.util.ArrayList;
import java.util.List;

public class TaskData {
    private static List<Task> taskList = new ArrayList<>();

    public static List<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(List<Task> tasks) {
        taskList = tasks;
    }
}
