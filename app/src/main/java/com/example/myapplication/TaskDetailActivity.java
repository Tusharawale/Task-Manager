package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private TextView textTaskDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        textTaskDetail = findViewById(R.id.textTaskDetail);

        // ✅ Properly retrieve Task object
        Task task = getIntent().getParcelableExtra("task");

        if (task != null) {
            StringBuilder details = new StringBuilder("Task: " + task.getName() + "\nSubtasks:\n");
            for (Subtask subtask : task.getSubtasks()) {
                details.append("- ").append(subtask.getSubtaskName()).append("\n");
            }
            textTaskDetail.setText(details.toString());
        } else {
            textTaskDetail.setText("No Task Details Available");
        }
    }
}
