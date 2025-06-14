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

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {
    private List<Subtask> units;
    private Context context;

    public UnitAdapter(Context context, List<Subtask> units) {
        this.context = context;
        this.units = units;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subtask unit = units.get(position);
        holder.unitText.setText(unit.getSubtaskName());

        // ✅ Handle Delete Unit Button
        holder.btnDeleteUnit.setOnClickListener(v -> {
            units.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, units.size());
            Toast.makeText(context, "Unit deleted", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView unitText;
        Button btnDeleteUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitText = itemView.findViewById(R.id.unitText);
            btnDeleteUnit = itemView.findViewById(R.id.btnDeleteUnit);
        }
    }
}
