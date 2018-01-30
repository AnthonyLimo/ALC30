package com.example.anthonykiplimo.alc30;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mRegUsername;
    private TextInputLayout mRegEmail;
    private TextInputLayout mRegPassword;
    private Button mRegButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mRegUsername = (TextInputLayout) findViewById(R.id.reg_username);
        mRegEmail = (TextInputLayout) findViewById(R.id.reg_email);
        mRegPassword = (TextInputLayout) findViewById(R.id.reg_password);
        mRegButton = (Button) findViewById(R.id.reg_button);

        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mRegUsername.getEditText().getText().toString();
                String email = mRegEmail.getEditText().getText().toString();
                String password = mRegPassword.getEditText().getText().toString();

                register_user(username, email, password);
            }
        });
    }

    private void register_user(String username, String email, String password) {
       mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                   startActivity(mainIntent);
                   finish();

               } else {
                   Toast.makeText(RegisterActivity.this, "You've got an error", Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}
