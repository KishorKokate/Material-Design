package com.example.bullsrent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;
    //Variabls
    Animation topanim, bottomanim;
    ImageView imageView;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        imageView.setAnimation(topanim);
        logo.startAnimation(bottomanim);
        slogan.startAnimation(bottomanim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, Login.class);
            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(imageView, "logo_image");
            pairs[1] = new Pair<View, String>(logo, "logo_text");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
            startActivity(intent, options.toBundle());


        }, SPLASH_SCREEN);

    }
}