package com.hdtgroup.hdt.firebasetodo.ui.create;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.hdtgroup.hdt.firebasetodo.network.firebase.FireBaseInstance;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;

public class CreatePresenter implements ICreateContract.IPresenter, Constants {
    private ICreateContract.IView mIView;
    private DatabaseReference mDatabase;

    public CreatePresenter(ICreateContract.IView iView) {
        this.mIView = iView;
        mDatabase = FireBaseInstance.getDatabaseReference();
    }

    @Override
    public void createTask(String nameTask) {
        String taskId = mDatabase.child(TASKS).push().getKey();
        if (taskId != null) {
            FireBaseInstance.getDatabaseReference().child(TASKS).child(taskId).setValue(nameTask)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mIView.showSuccess();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mIView.showFailure();
                        }
                    });
        } else {
            mIView.showFailure();
        }
    }

    @Override
    public void updateTask(String keyTask, String nameTask) {
        FireBaseInstance.getDatabaseReference().child(TASKS).child(keyTask).setValue(nameTask)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mIView.showSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mIView.showFailure();
                    }
                });
    }
}
