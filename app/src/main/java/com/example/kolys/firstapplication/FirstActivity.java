package com.example.kolys.firstapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String NAME_EXTRA = "name";
    public static final String EMAIL_EXTRA = "email";
    public static final String PHONE_EXTRA = "phone";
    public final int SECOND_REQUEST_CODE = 10;
    public final int SHARING_REQUEST_CODE = 228;
    public TextView nameTV;
    public TextView emailTV;
    public TextView phoneTV;
    public Button editBtn;
    public Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        nameTV = findViewById(R.id.tv_name);
        emailTV = findViewById(R.id.tv_email);
        phoneTV = findViewById(R.id.tv_phone);
        editBtn = findViewById(R.id.btn_edit);
        shareBtn = findViewById(R.id.btn_share);
        editBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    nameTV.setText(data.getStringExtra(SecondActivity.NAME_EXTRA));
                    emailTV.setText(data.getStringExtra(SecondActivity.EMAIL_EXTRA));
                    phoneTV.setText(data.getStringExtra(SecondActivity.PHONE_EXTRA));
            }
        }
        if (requestCode == SHARING_REQUEST_CODE){
            String msg = "";
            switch (resultCode) {
                case RESULT_OK:
                    msg = "Sending success!";
                    break;
                case RESULT_CANCELED:
                    msg = "Sending failed!";
            }
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra(NAME_EXTRA,nameTV.getText().toString());
                intent.putExtra(EMAIL_EXTRA, emailTV.getText().toString());
                intent.putExtra(PHONE_EXTRA, phoneTV.getText().toString());
                startActivityForResult(intent, SECOND_REQUEST_CODE);
                break;

            case R.id.btn_share:
                if(!nameTV.getText().toString().isEmpty()) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, nameTV.getText().toString());
                    sendIntent.setType("text/plain");
                    startActivityForResult(Intent.createChooser(sendIntent, getResources().getText(R.string.title)), SHARING_REQUEST_CODE);
                }
        }

    }
}
