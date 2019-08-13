package com.hdtgroup.hdt.firebasetodo.ui.read;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hdtgroup.hdt.firebasetodo.entities.FirebaseRequest;
import com.hdtgroup.hdt.firebasetodo.entities.HttpResponse;
import com.hdtgroup.hdt.firebasetodo.entities.Task;
import com.hdtgroup.hdt.firebasetodo.network.firebase.FireBaseInstance;
import com.hdtgroup.hdt.firebasetodo.network.retrofit.RequestService;
import com.hdtgroup.hdt.firebasetodo.network.retrofit.RetrofitInstance;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        mDatabase.child(TASKS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void deleteTask(String key) {
        mDatabase.child(TASKS).child(key).removeValue();
    }

    @Override
    public void sendNotyFireBase() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<InstanceIdResult> task) {
                RequestService retrofitService = RetrofitInstance.getRetrofitInstance().create(RequestService.class);
                String token = "e-Rw6KAeJKk:APA91bGRMNE56KbndDGnYZm6NtNlhmn5VdTyEXCwW1dNpppFebxjQhD2Th6QCN8NNFNG5cvh9Vlg12giFvR-IYwInjXwNttH1RFQm7R6il0AvpDlh1XMTjEFKrG5utD_W8lTaqnDKo-0";
                Call<HttpResponse> call = retrofitService.postRequest(SERVER_KEY, new FirebaseRequest(token, "Test 4", "D to D").bodyRequest());
                call.enqueue(new Callback<HttpResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<HttpResponse> call, @NonNull Response<HttpResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.i(TAG, "onResponse: " + response.body().getResults());
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<HttpResponse> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: ");
                    }
                });
            }
        });
    }
}
