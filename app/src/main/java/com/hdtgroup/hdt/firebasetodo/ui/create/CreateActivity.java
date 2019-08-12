package com.hdtgroup.hdt.firebasetodo.ui.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hdtgroup.hdt.firebasetodo.R;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;
import com.hdtgroup.hdt.firebasetodo.utils.Utils;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener, ICreateContract.IView, Constants {
    private ICreateContract.IPresenter mCreatePresenter;
    private TextInputEditText mEdtTaskName;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        FloatingActionButton fabCreate = findViewById(R.id.fab_create);
        fabCreate.setOnClickListener(this);
        FloatingActionButton fabCancel = findViewById(R.id.fab_cancel);
        fabCancel.setOnClickListener(this);

        mEdtTaskName = findViewById(R.id.edt_taskname);

        intent = getIntent();
        if (intent != null) {
            mEdtTaskName.setText(intent.getStringExtra(NAME_TASK));
        }

        mCreatePresenter = new CreatePresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_create: {
                if (Utils.isNotEmpty(mEdtTaskName)) {
                    if (intent.getStringExtra(KEY_TASK) != null) {
                        mCreatePresenter.updateTask(intent.getStringExtra(KEY_TASK), mEdtTaskName.getText().toString());
                    } else {
                        mCreatePresenter.createTask(mEdtTaskName.getText().toString());
                    }
                }
                break;
            }
            case R.id.fab_cancel: {
                CreateActivity.this.finish();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Create Success!", Toast.LENGTH_SHORT).show();
        CreateActivity.this.finish();
    }

    @Override
    public void showFailure() {
        Toast.makeText(this, "Create Failure!", Toast.LENGTH_SHORT).show();
        CreateActivity.this.finish();
    }
}
