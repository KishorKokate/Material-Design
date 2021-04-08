package com.example.bullsrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.royrodriguez.transitionbutton.TransitionButton;

public class Signup extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhoneno, regPassword;
    Button regBtn, regToLoginBtn;
    private TransitionButton transitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regName = findViewById(R.id.Name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneno = findViewById(R.id.phoneno);
        regBtn = findViewById(R.id.signup_btn);
        regPassword = findViewById(R.id.password);
        regToLoginBtn = findViewById(R.id.regtologin_btn);




        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the loading animation when the user tap the button
                transitionButton.startAnimation();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean isSuccessful = true;

                        // Choose a stop animation if your call was succesful or not
                        if (isSuccessful) {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                                @Override
                                public void onAnimationStopEnd() {
                                    Intent intent = new Intent(getBaseContext(), OTPActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 2000);
            }
        });


    }



    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpaces = "(>=\\s+$)";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username is too long");
            return false;
        } else if (!val.matches(noWhiteSpaces)) {
            regUsername.setError("White Spaces are Not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailpattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneno.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhoneno.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneno.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }

    public void StoreUserData(View view) {

        //  if (!validateEmail() | !validateName()  | !validatePhoneNo() | !validateUserName()) {
        //      return;
        //  }


     //   String name = regName.getEditText().getText().toString().trim();
       // String username = regUsername.getEditText().getText().toString().trim();
       // String email = regEmail.getEditText().getText().toString().trim();
        String phoneNo = regPhoneno.getEditText().getText().toString().trim();
      //  String password = regPassword.getEditText().getText().toString().trim();
         Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
        intent.putExtra("mobile", phoneNo);
     //   intent.putExtra("Name", name);
      //  intent.putExtra("userName", username);
      //  intent.putExtra("Email", email);
      //  intent.putExtra("password", password);
        startActivity(intent);

    }
    public void callloginpage(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }


}