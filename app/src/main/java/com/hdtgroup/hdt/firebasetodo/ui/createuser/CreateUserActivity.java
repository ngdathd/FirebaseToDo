package com.hdtgroup.hdt.firebasetodo.ui.createuser;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hdtgroup.hdt.firebasetodo.R;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText mEdtUsername;
    private TextInputEditText mEdtPhone;
    private TextInputEditText mEdtAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        FloatingActionButton fabCreate = findViewById(R.id.fab_create);
        fabCreate.setOnClickListener(this);
        FloatingActionButton fabCancel = findViewById(R.id.fab_cancel);
        fabCancel.setOnClickListener(this);

        mEdtUsername = findViewById(R.id.edt_taskname);
    }

    @Override
    public void onClick(View view) {

    }
}
