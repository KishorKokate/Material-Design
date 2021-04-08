package com.example.bullsrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class DashBoard extends AppCompatActivity {
    TextView name,Mail;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        name=findViewById(R.id.name);
        Mail=findViewById(R.id.dashboardemail);

        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            Mail.setText(signInAccount.getEmail());
        }
    }
    public void callloginpage(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}