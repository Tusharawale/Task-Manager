package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskText.setText(task.getName());

        // ✅ Delete Task Button Functionality
        holder.btnDelete.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                tasks.remove(currentPosition);
                notifyItemRemoved(currentPosition);
                notifyItemRangeChanged(currentPosition, tasks.size());
                Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup buttons for adding subtasks
        holder.btnUnit.setOnClickListener(v -> addSubtask(task, "SUBTASK 1"));
        holder.btnAssignment.setOnClickListener(v -> addSubtask(task, "SUBTASK 2"));
        holder.btnTest.setOnClickListener(v -> addSubtask(task, "SUBTASK 3"));
        holder.btnQuiz.setOnClickListener(v -> addSubtask(task, "SUBTASK 4"));

        // Display subtasks
        holder.subtaskContainer.removeAllViews();
        for (Subtask subtask : task.getSubtasks()) {
            View subtaskView = LayoutInflater.from(context).inflate(R.layout.subtask_item, null);
            TextView subtaskName = subtaskView.findViewById(R.id.subtaskText);
            Button deleteButton = subtaskView.findViewById(R.id.btnDeleteSubtask);

            subtaskName.setText(subtask.getSubtaskName());
            deleteButton.setOnClickListener(v -> {
                task.getSubtasks().remove(subtask);
                notifyDataSetChanged();
            });

            holder.subtaskContainer.addView(subtaskView);
        }
    }

    private void addSubtask(Task task, String subtaskType) {
        Subtask newSubtask = new Subtask(subtaskType);
        task.addSubtask(newSubtask);
        notifyDataSetChanged();
        Toast.makeText(context, subtaskType + " subtask added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;
        Button btnDelete, btnUnit, btnAssignment, btnTest, btnQuiz;
        ViewGroup subtaskContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUnit = itemView.findViewById(R.id.btnUnit);
            btnAssignment = itemView.findViewById(R.id.btnAssignment);
            btnTest = itemView.findViewById(R.id.btnTest);
            btnQuiz = itemView.findViewById(R.id.btnQuiz);
            subtaskContainer = itemView.findViewById(R.id.subtaskContainer);
        }
    }
}
