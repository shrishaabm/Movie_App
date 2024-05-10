package com.example.movie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.movie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends MainActivity{
    EditText user,pass;
    Button login,tosignup,forget;




    @Override
    protected void onCreate(Bundle save)
    {
        super.onCreate(save);
        setContentView(R.layout.login_pg);
        user=findViewById(R.id.use);
        pass=findViewById(R.id.pas);
        login=findViewById(R.id.login);
        tosignup=findViewById(R.id.register);
        forget=findViewById(R.id.forget);
        setVariable();
        register();

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
            }
        });

    }



    private void register() {
        tosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    private void setVariable() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=user.getText().toString();
                String password=pass.getText().toString();
                if(!email.isEmpty() && !password.isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Please Fill Username And Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
