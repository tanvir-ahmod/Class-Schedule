package com.example.shoukhin.classroutine.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.R;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ItemViewHolder> {

    private List<RoutineModel> itemList;
    private Context context;

    public RoutineAdapter(List<RoutineModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoutineAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineAdapter.ItemViewHolder holder, final int position) {
        holder.startTimeTextView.setText(itemList.get(position).getStartTime());
        holder.courseCodeTextView.setText(itemList.get(position).getCourseCode());
        holder.endTimeTextView.setText(itemList.get(position).getEndTime());
        holder.courseNameTextView.setText(itemList.get(position).getCourseName());
        holder.roomNumberTextView.setText(itemList.get(position).getRoomNumber());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView courseNameTextView;
        public TextView courseCodeTextView;
        public TextView roomNumberTextView;
        public TextView startTimeTextView;
        public TextView endTimeTextView;

        public ItemViewHolder(View view) {
            super(view);
            courseNameTextView = view.findViewById(R.id.listviewCourseName);
            courseCodeTextView = view.findViewById(R.id.listviewCourseCode);
            endTimeTextView = view.findViewById(R.id.listviewTotimetbx);
            startTimeTextView = view.findViewById(R.id.listviewFromtimetbx);
            roomNumberTextView = view.findViewById(R.id.listviewRoomNumbertbx);
        }
    }
}
