package com.adamapps.a4snaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12-Jun-17.
 */

public class PhoneActivity extends AppCompatActivity {
    EditText number;
    FirebaseAuth mAuth;
    String verID;
    String resendToken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        number = (EditText) findViewById(R.id.phoneNumber);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signPhone(View v){
        String num = number.getText().toString();


        PhoneAuthProvider.getInstance().verifyPhoneNumber("+237"+num,60, TimeUnit.SECONDS,PhoneActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if(e instanceof FirebaseTooManyRequestsException){
                    Toast.makeText(PhoneActivity.this, "Can't Your Phone Auth For Now. Sign In with Email Instead please", Toast.LENGTH_SHORT).show();
                    Toast.makeText(PhoneActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                else if(e instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(PhoneActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                    Toast.makeText(PhoneActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verID = verificationId;
                resendToken =  forceResendingToken.toString();

            }

        });

    }
    PhoneAuthCredential credential = PhoneAuthProvider.getCredential("string", "string");

    private void signInWithPhoneAuthCredential (PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            //Finish Current Activity and Start MAinActivity
                            startActivity(new Intent(PhoneActivity.this,MainActivity.class));
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(PhoneActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PhoneActivity.this, "Could Not Verify Check Network", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
