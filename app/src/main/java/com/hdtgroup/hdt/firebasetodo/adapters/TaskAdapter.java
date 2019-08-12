package com.hdtgroup.hdt.firebasetodo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.hdtgroup.hdt.firebasetodo.R;
import com.hdtgroup.hdt.firebasetodo.entities.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter {
    private List<Task> mTasks;
    private OnClickButtonTask onClickButtonTask;

    public TaskAdapter(List<Task> tasks, OnClickButtonTask onClickButtonTask) {
        this.mTasks = tasks;
        this.onClickButtonTask = onClickButtonTask;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskVH taskVH = (TaskVH) holder;
        taskVH.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public interface OnClickButtonTask {
        void clickBtnUpdate(String key, String name);

        void clickBtnDelete(String key);
    }

    private class TaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTaskName;
        private AppCompatButton btnUpdate;
        private AppCompatButton btnDelete;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_taskName);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            btnUpdate.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        private void bindView(int position) {
            tvTaskName.setText(mTasks.get(position).getNameTask());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_update: {
                    onClickButtonTask.clickBtnUpdate(mTasks.get(getAdapterPosition()).getKeyTask(), mTasks.get(getAdapterPosition()).getNameTask());
                    break;
                }
                case R.id.btn_delete: {
                    onClickButtonTask.clickBtnDelete(mTasks.get(getAdapterPosition()).getKeyTask());
                    break;
                }
                default:
                    break;
            }
        }
    }
}
