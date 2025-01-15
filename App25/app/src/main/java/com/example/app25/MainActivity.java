package com.example.app25;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app25.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.HashMap;

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

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        // insert
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> document = new HashMap<>();
                document.put("fname","Dananji");
                document.put("lname","Jayasundara");
                document.put("mobile","0779177299");
                document.put("city","Colombo");
                document.put("gender","Female");

                User user = new User();
                user.setFname("Dananji");
                user.setLname("Jayasundara");
                user.setMobile("0779177299");
                user.setCity("Colombo");
                user.setGender("Female");


                firestore.collection("user").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i("logApp25","Document inserted with ID: "+documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("logApp25","Document insertion failed");
                            }
                        });

            }
        });

        // update
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> document = new HashMap<>();
                document.put("city","Kandy");

                firestore.collection("user").document("wH421936AWksWe5I3mZ8").update(document)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.i("logApp25","Document updated");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("logApp25","Document update failed");
                            }
                        });

            }
        });

        // delete
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firestore.collection("user").document("wH421936AWksWe5I3mZ8").delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.i("logApp25","Document deleted");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("logApp25","Document deletion failed");
                            }
                        });

            }
        });

        // search
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firestore.collection("user").document("wH421936AWksWe5I3mZ8").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if(task.isSuccessful()){

                                    DocumentSnapshot document = task.getResult();
                                    if(document.exists()){
//                                        Log.i("logApp25","Document found: "+String.valueOf(document.getData()));
//                                        Log.i("logApp25","First Name: "+String.valueOf(document.get("fname")));

                                        User user = document.toObject(User.class);
                                        Log.i("logApp25","First Name: "+user.getFname());
                                        Log.i("logApp25","Last Name: "+user.getLname());

                                    }else{
                                        Log.i("logApp25","Document not found");
                                    }

                                }

                            }
                        });

            }
        });

        // offline search
        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firestore.collection("user").document("wH421936AWksWe5I3mZ8").get(Source.CACHE)
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if(task.isSuccessful()){

                                    DocumentSnapshot document = task.getResult();
                                    if(document.exists()){
                                        Log.i("logApp25","Document found: "+String.valueOf(document.getData()));
                                        Log.i("logApp25","First Name: "+String.valueOf(document.get("fname")));
                                    }else{
                                        Log.i("logApp25","Document not found");
                                    }

                                }

                            }
                        });

            }
        });

    }
}