package com.example.app20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = findViewById(R.id.imageView);
                SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y);

                SpringForce springForce = new SpringForce();
                springForce.setStiffness(SpringForce.STIFFNESS_LOW);
                springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                springForce.setFinalPosition(1000f);

                springAnimation.setSpring(springForce);
                springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                        //Toast.makeText(MainActivity.this,"End",Toast.LENGTH_LONG).show();
                    }
                });
                springAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
                    @Override
                    public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                        //Toast.makeText(MainActivity.this,"update:"+String.valueOf(value),Toast.LENGTH_LONG).show();
                    }
                });
                springAnimation.start();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = findViewById(R.id.imageView);
                FlingAnimation flingAnimation = new FlingAnimation(imageView,DynamicAnimation.TRANSLATION_Y);

                flingAnimation.setStartVelocity(1000f);
                flingAnimation.setFriction(0.2f);

                flingAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                        Toast.makeText(MainActivity.this, "end fling", Toast.LENGTH_SHORT).show();
                    }
                });
                flingAnimation.start();

            }
        });
    }
}