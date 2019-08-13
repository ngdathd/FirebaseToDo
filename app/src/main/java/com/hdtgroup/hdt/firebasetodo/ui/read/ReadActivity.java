package com.hdtgroup.hdt.firebasetodo.ui.read;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hdtgroup.hdt.firebasetodo.R;
import com.hdtgroup.hdt.firebasetodo.adapters.TaskAdapter;
import com.hdtgroup.hdt.firebasetodo.entities.Task;
import com.hdtgroup.hdt.firebasetodo.ui.create.CreateActivity;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;

import java.util.List;

public class ReadActivity extends AppCompatActivity implements IReadContract.IView, View.OnClickListener, TaskAdapter.OnClickButtonTask, Constants {
    private RecyclerView mRcvTasks;
    private IReadContract.IPresenter mReadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        mRcvTasks = findViewById(R.id.rcv_tasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRcvTasks.setLayoutManager(linearLayoutManager);

        FloatingActionButton fabCreate = findViewById(R.id.fab_add_task);
        fabCreate.setOnClickListener(this);
        FloatingActionButton fabSend = findViewById(R.id.fab_send);
        fabSend.setOnClickListener(this);

        mReadPresenter = new ReadPresenter(this);
        mReadPresenter.readTasks();
    }

    @Override
    public void showSuccess(List<Task> tasks) {
        TaskAdapter taskAdapter = new TaskAdapter(tasks, this);
        mRcvTasks.setAdapter(taskAdapter);
    }

    @Override
    public void showFailure() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_task: {
                Intent intent = new Intent(ReadActivity.this, CreateActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.fab_send:{
                mReadPresenter.sendNotyFireBase();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void clickBtnUpdate(String key, String name) {
        Intent intent = new Intent(ReadActivity.this, CreateActivity.class);
        intent.putExtra(KEY_TASK, key);
        intent.putExtra(NAME_TASK, name);
        startActivity(intent);
    }

    @Override
    public void clickBtnDelete(String key) {
        mReadPresenter.deleteTask(key);
    }
}
