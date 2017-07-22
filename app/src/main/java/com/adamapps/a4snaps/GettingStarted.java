package com.adamapps.a4snaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;

public class GettingStarted extends AppCompatActivity {

    EditText emailText,passText;
    Button signIn,signUp;
    TextView forgotPass;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.showOverflowMenu();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        listener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user!=null){
                    finish();
                    startActivity(new Intent(GettingStarted.this,MainActivity.class));

                }
            }
        };

        emailText = (EditText) findViewById(R.id.emailText);
        passText = (EditText) findViewById(R.id.passwordText);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
        forgotPass = (TextView) findViewById(R.id.forgotPassword);
    }

    public void phoneAuth(View v){
        startActivity(new Intent(this,PhoneActivity.class));
    }

    public void SignIn(View v){
        String email,password;
        email = emailText.getText().toString().trim();
        password = passText.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(GettingStarted.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GettingStarted.this,MainActivity.class));
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GettingStarted.this, "Could Not Sign In\n"+e, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }
    public void SignUp(final View signUp){
        final String email,password;
        email = emailText.getText().toString().trim();
        password = passText.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(GettingStarted.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GettingStarted.this,MainActivity.class));
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                auth.fetchProvidersForEmail(email).addOnSuccessListener(new OnSuccessListener<ProviderQueryResult>() {
                    @Override
                    public void onSuccess(ProviderQueryResult providerQueryResult) {
                        Toast.makeText(GettingStarted.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GettingStarted.this, "Could Not Sign Up\n"+e, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.addAuthStateListener(listener);
    }
}
