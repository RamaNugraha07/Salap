package com.example.sistem_sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button login;
    TextView details;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()){

                } else {
                    checkuser();
                }
            }
        });
    }

    public boolean validateUsername(){
        String val = user.getText().toString();
        if (val.isEmpty()){
            user.setError("User cannot be empty");
            return false;
        } else {
            user.setError(null);
            return true;
        }
    }
    public boolean validatePassword(){
        String val = password.getText().toString();
        if (val.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkuser(){
        String setUsername = user.getText().toString().trim();
        String setPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(setUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    user.setError(null);
                    String passwordFromDB = snapshot.child(setUsername).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, setPassword)){
                        user.setError(null);
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);
                    } else {
                        password.setError("Invalid Password");
                        password.requestFocus();
                    }
                } else {
                    user.setError("Uset does not exist");
                    user.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}