package com.example.app3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button signInBtn = findViewById(R.id.buttonSignIn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mobile = findViewById(R.id.editTextMobile);
                EditText password = findViewById(R.id.editTextPassword);

                String mobileText = mobile.getText().toString();
                String passwordText = password.getText().toString();

                if(mobileText.isEmpty() || passwordText.isEmpty()){

                    mobile.setError("Field can not be empty");
                    password.setError("Field can not be empty");

                    Log.w("Login","Missing Information");
                }else{

                    if(mobileText.equals("0740211671") && passwordText.equals("12345678")){
                        Log.i("Login","Success");

                        Intent i = new Intent(SignInActivity.this,HomeActivity.class);
                        startActivity(i);
                    }else{
                        Log.e("Login","Failed");
                    }

                }

            }
        });

    }
}