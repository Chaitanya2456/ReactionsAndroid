package com.example.android.tabswithswipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tabswithswipes.Utils.Models;
import com.example.android.tabswithswipes.Utils.utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.Buffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity {

    private static EditText fullName, username, emailId, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private FirebaseAuth auth;
    private static TextView forgotPassword;
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference myRef;
    private int usernameexists = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullName = (EditText)findViewById(R.id.fullName);
        emailId = (EditText)findViewById(R.id.userEmailId);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        signUpButton = (Button)findViewById(R.id.signUpBtn);
        login = (TextView)findViewById(R.id.already_user);
        username = (EditText)findViewById(R.id.username);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);
        auth = FirebaseAuth.getInstance();
        mFirebasedatabase = FirebaseDatabase.getInstance();
        myRef = mFirebasedatabase.getReference();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(signupActivity.this, resetPassword.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkValidation(){

        String getFullName = fullName.getText().toString();
        final String getEmailId = emailId.getText().toString();
        String getPassword = password.getText().toString();
        final String getusernamehere = username.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id

        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0
                || getusernamehere.length()==0){
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
        }else if(!emailValidator(getEmailId)){
            Toast.makeText(this,"Email id is invalid", Toast.LENGTH_SHORT).show();
        }else if(!getConfirmPassword.equals(getPassword)){
            Toast.makeText(this,"Passwords don't match", Toast.LENGTH_SHORT).show();
        }else if(getPassword.length()<6){
            Toast.makeText(this, "Password too short, enter minimum 6 characters", Toast.LENGTH_SHORT)
                    .show();
        }else{

            auth.createUserWithEmailAndPassword(getEmailId, getPassword)
                    .addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(signupActivity.this, "createUserWithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if(!task.isSuccessful()){
                                Toast.makeText(signupActivity.this, "Authentication failed" + task.getException(), Toast.LENGTH_SHORT).show();
                            }else{
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Models user = new Models();
                                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                                            Log.d("TAG",ds.toString());
                                            user.setUsername(ds.getValue(Models.class).getUsername());
                                            if(ds.getValue(Models.class).getUsername().equals(getusernamehere)){
                                                usernameexists = 1;
                                                break;
                                            }

                                        }
                                        if(usernameexists == 0){
                                            Models users = new Models(getEmailId,getusernamehere);
                                            String UserID = auth.getCurrentUser().getUid();
                                            myRef.child("users").child(UserID).setValue(users);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                //DataSnapshot dataSnapshot = null;
                                startActivity(new Intent(signupActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }

    }

    private boolean emailValidator(String emailId){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailId);
        return matcher.matches();
    }
}
