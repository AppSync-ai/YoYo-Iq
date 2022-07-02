package com.example.yoyoiq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterDetails extends AppCompatActivity {
    TextView backPress, registerUser;
    DatabaseReference databaseReference;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    TextView userName;
    TextView mobileNo;
    TextView emailId;
    TextView password;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);
        initMethod();
        setAction();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Data Uploading..");
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        registerUser = findViewById(R.id.registerUserDetails);
        userName = findViewById(R.id.userName);
        mobileNo = findViewById(R.id.mobileNo);
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseConnectivity.getDatabasePath(this);
    }

    private void setAction() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
    }

    private boolean validation() {
        boolean isValid = true;
        try {
            if (userName.getText().toString().trim().length() == 0) {
                userName.setError("Please enter user name");
                userName.requestFocus();
                isValid = false;
            } else if (mobileNo.getText().toString().trim().length() == 0) {
                mobileNo.setError("Please enter mobile number");
                mobileNo.requestFocus();
                isValid = false;
            } else if (emailId.getText().toString().trim().length() == 0) {
                emailId.setError("Please enter email");
                emailId.requestFocus();
                isValid = false;
            } else if (password.getText().toString().trim().length() == 0) {
                password.setError("Please enter password");
                password.requestFocus();
                isValid = false;
            } else {
                progressDialog.show();
                insertRegisterData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void insertRegisterData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userName", userName.getText().toString());
        data.put("mobileNo", mobileNo.getText().toString());
        data.put("emailId", emailId.getText().toString());
        data.put("password", password.getText().toString());
        databaseConnectivity.getDatabasePath(this).child("RegisterDetails").push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                showDialog("User Register Successfully..", true);
            }
        });
    }

    public void showDialog(String message, Boolean isFinish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.dismiss();
            if (isFinish) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}