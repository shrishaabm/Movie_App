package com.example.movie.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.movie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class ForgetActivity extends MainActivity{
    EditText id;
    Button submit;
    @Override
    protected void onCreate(Bundle save)
    {
        super.onCreate(save);
        setContentView(R.layout.forget_pass_pg);
        id=findViewById(R.id.emid);
        submit=findViewById(R.id.submit);
        //reset();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid=id.getText().toString().trim();
                if(!TextUtils.isEmpty((emailid))){
                    mAuth.sendPasswordResetEmail(emailid).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(ForgetActivity.this, "Reset Password has been sent to your email id", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ForgetActivity.this,LoginActivity.class));

                                    }
                                    else
                                    {
                                        Toast.makeText(ForgetActivity.this, "Error resetting", Toast.LENGTH_SHORT).show();
                                    }


                                }

                            });

                }
                else
                {
                    Toast.makeText(ForgetActivity.this, "email field cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }


}
