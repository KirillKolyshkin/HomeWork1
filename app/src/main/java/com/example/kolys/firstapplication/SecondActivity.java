package com.example.kolys.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAME_EXTRA = "name";
    public static final String EMAIL_EXTRA = "email";
    public static final String PHONE_EXTRA = "phone";
    public EditText nameET;
    public EditText emailET;
    public EditText phoneET;
    public Button cancelBtn;
    public Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameET = findViewById(R.id.et_name);
        emailET = findViewById(R.id.et_email);
        phoneET = findViewById(R.id.et_phone);
        cancelBtn = findViewById(R.id.btn_cancel);
        saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        Intent data = getIntent();
        if (data.getStringExtra(FirstActivity.NAME_EXTRA) != null) {
            nameET.setText(data.getStringExtra(FirstActivity.NAME_EXTRA));
            emailET.setText(data.getStringExtra(FirstActivity.EMAIL_EXTRA));
            phoneET.setText(data.getStringExtra(FirstActivity.PHONE_EXTRA));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (nameET.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter your name!", Toast.LENGTH_LONG).show();
                } else if (emailET.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter your email!", Toast.LENGTH_LONG).show();
                } else if (phoneET.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter your phone number!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(NAME_EXTRA, nameET.getText().toString());
                    intent.putExtra(EMAIL_EXTRA, emailET.getText().toString());
                    intent.putExtra(PHONE_EXTRA, phoneET.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                finish();
        }
    }
}
