package com.example.bullsrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    Button verifybtn;
    EditText phoneNoEnteredByUser;
    String otpid, phonenumber, name, username, email, password;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        verifybtn = findViewById(R.id.verify_btn);
        phoneNoEnteredByUser = findViewById(R.id.Verification_Code_Entered_by_User);
        mAuth = FirebaseAuth.getInstance();

        phonenumber = getIntent().getStringExtra("mobile");
      //  name = getIntent().getStringExtra("Name");
      //  username = getIntent().getStringExtra("userName");
      //  email = getIntent().getStringExtra("Email");
      //  password = getIntent().getStringExtra("password");
        sendVerificationCodeToUser();

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phoneNoEnteredByUser.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Blank Field cannot be processed", Toast.LENGTH_SHORT).show();
                } else if (phoneNoEnteredByUser.getText().toString().length() != 6) {
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, phoneNoEnteredByUser.getText().toString());
                    signInTheUserByCredential(credential);
                }
            }
        });

    }

    private void sendVerificationCodeToUser() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + this.phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            otpid = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            signInTheUserByCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };


    private void signInTheUserByCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),DashBoard.class));
                            Toast.makeText(OTPActivity.this, "Verification completed", Toast.LENGTH_SHORT).show();
                          //  storeUserData();

                        } else {
                            Toast.makeText(OTPActivity.this, "Sign In Code Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

   // private void storeUserData() {
       // FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
     //   DatabaseReference reference = rootNode.getReference("students");

      //  UserHelperClass helperClass = new UserHelperClass(name, username, email, phonenumber, password);
//
      //  reference.setValue(helperClass);
      //  Toast.makeText(OTPActivity.this, "value Inserted!", Toast.LENGTH_SHORT).show();

   // }
}