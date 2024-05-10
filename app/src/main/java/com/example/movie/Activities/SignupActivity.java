package com.example.movie.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends MainActivity {
    EditText user,pass;
    Button sign,log;


    @Override
    protected void onCreate(Bundle save)
    {
        super.onCreate(save);
        setContentView(R.layout.signup_pg);
        user=(EditText)findViewById(R.id.username);
        pass=findViewById(R.id.password);
        sign=findViewById(R.id.signup);
        log=findViewById(R.id.login);

        setVariable();
        login();

    }

    private void login() {
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }

    private void setVariable() {
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=user.getText().toString();
                String password=pass.getText().toString();
                String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
                if(email.equals(emailPattern))
                {
                    Toast.makeText(SignupActivity.this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                }

                else if(password.length()<6)
                {
                    Toast.makeText(SignupActivity.this, "Password Must be at least 6 characters", Toast.LENGTH_SHORT).show();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete())
                            {
                                Log.i(TAG,"onComplete");
                                startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Log.i(TAG,"Failure: "+task.getException());
                                Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

}
