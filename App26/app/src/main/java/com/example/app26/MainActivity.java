package com.example.app26;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.List;

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

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                HashMap<String, Object> user = new HashMap<>();
                user.put("fname", "Kamal");
                user.put("lname", "Niyan");
                user.put("gender", "Male");
                user.put("city", "Kandy");
                user.put("mobile", "0717900130");

                firestore.collection("user").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("TAG", "Error adding document", e);
                            }
                        });

            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("user").document("SqDSUpAnDTZpO8uhI8WV").get(Source.DEFAULT)
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if(task.isSuccessful()){
                                    DocumentSnapshot document = task.getResult();
                                    if(document.exists()){
                                        Log.i("TAG", "DocumentSnapshot data: " + document.getData());
                                    }else{
                                        Log.i("TAG", "No such document");
                                    }
                                }else{
                                    Log.i("TAG", "get failed with ", task.getException());
                                }

                            }
                        });

            }
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("user").document("SqDSUpAnDTZpO8uhI8WV")
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {

                                if(document != null){
                                    if(document.exists()){
                                        Log.i("TAG", "notification: " + document.getData());
                                    }
                                }

                            }
                        });

            }
        });

        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("user")

                        //.whereEqualTo("city","Colombo")
                        //.whereEqualTo("gender","Male")

                        //.where(Filter.equalTo("city","Colombo"))
                        //.where(Filter.equalTo("gender","Male"))

                        .where(
                                Filter.and(
                                        Filter.equalTo("city","Colombo"),
                                        Filter.equalTo("gender","Male")
                                )
                        )

                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();
                                for (DocumentSnapshot documentSnapshot : documentSnapshotList){
                                    Log.i("TAG", "DocumentSnapshot data: " + documentSnapshot.getData());
                                }

                            }
                        });

            }
        });

        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("user")
                        .whereEqualTo("city","Colombo")
                        .orderBy("fname", Query.Direction.DESCENDING)
                        .limit(5)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();
                                for(DocumentSnapshot document:documentSnapshotList){
                                    Log.i("TAG", "DocumentSnapshot data: " + document.getData());
                                }

                            }
                        });

            }
        });

    }
}