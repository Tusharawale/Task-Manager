package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {
    private LinearLayout listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listViewTasks = findViewById(R.id.listViewTasks);
        ArrayList<Task> tasks = getIntent().getParcelableArrayListExtra("tasks");

        if (tasks != null) {
            for (Task task : tasks) {
                TextView textView = new TextView(this);
                textView.setText(task.getName());
                textView.setTextSize(18);
                textView.setPadding(10, 10, 10, 10);
                textView.setOnClickListener(v -> {
                    Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
                    intent.putExtra("task", task);
                    startActivity(intent);
                });
                listViewTasks.addView(textView);
            }
        }
    }
}
