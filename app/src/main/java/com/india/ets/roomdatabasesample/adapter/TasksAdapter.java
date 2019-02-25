package com.india.ets.roomdatabasesample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.india.ets.roomdatabasesample.R;
import com.india.ets.roomdatabasesample.UpdateTaskActivity;
import com.india.ets.roomdatabasesample.roomdatabase.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    private Context mCtx;
    private List<Task> taskList;

    public TasksAdapter(Context mCtx, List<Task> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.activity_recyclerview_tasks_child,viewGroup,false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textViewTask.setText(task.getTask());
        holder.textViewDesc.setText(task.getDesc());
        holder.textViewFinishBy.setText(task.getFinishBy());

        if (task.isFinished())
            holder.textViewStatus.setText("Completed");
        else
            holder.textViewStatus.setText("Not Completed");
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task", task);

            mCtx.startActivity(intent);
        }
    }

}
