package com.hdtgroup.hdt.firebasetodo.ui.read;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hdtgroup.hdt.firebasetodo.entities.Task;
import com.hdtgroup.hdt.firebasetodo.network.firebase.FireBaseInstance;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ReadPresenter implements IReadContract.IPresenter, Constants {
    private IReadContract.IView mIView;
    private DatabaseReference mDatabase;

    public ReadPresenter(IReadContract.IView iView) {
        this.mIView = iView;
        mDatabase = FireBaseInstance.getDatabaseReference();
    }

    @Override
    public void readTasks() {
        mDatabase.child(TASKS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Task> tasks = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    String taskName = keyNode.getValue(String.class);
                    tasks.add(new Task(keyNode.getKey(), taskName));
                }
                mIView.showSuccess(tasks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mIView.showFailure();
            }
        });
    }

    @Override
    public void deleteTask(String key) {
        mDatabase.child(TASKS).child(key).removeValue();
    }
}
