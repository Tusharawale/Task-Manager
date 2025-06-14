package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTask;
    private Button btnAddTask, btnViewTasks;
    private RecyclerView recyclerViewTasks;
    private List<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTask = findViewById(R.id.editTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnViewTasks = findViewById(R.id.btnViewTasks);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        btnAddTask.setOnClickListener(v -> addTask());

        btnViewTasks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
            intent.putParcelableArrayListExtra("tasks", new ArrayList<>(taskList));
            startActivity(intent);
        });
    }

    private void addTask() {
        String taskName = editTask.getText().toString().trim();
        if (!taskName.isEmpty()) {
            Task newTask = new Task(taskName);
            taskList.add(newTask);
            taskAdapter.notifyDataSetChanged();
            editTask.setText("");
        }
    }
}
