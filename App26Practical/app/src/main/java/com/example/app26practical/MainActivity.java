package com.example.app26practical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        // brand
        Spinner spinner = findViewById(R.id.spinner);

        List<String> brandList = new ArrayList<>();
        brandList.add("Apple");
        brandList.add("Samsung");
        brandList.add("Huawei");

        ArrayAdapter<String> brandArrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                brandList
        );
        spinner.setAdapter(brandArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String brand = String.valueOf(spinner.getSelectedItem());
                firestore.collection("product")
                        .where(Filter.equalTo("brand", brand))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {

                                    List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();

                                    for (DocumentSnapshot documentSnapshot : documentSnapshotList) {
                                        Log.e("TAG", "success:"+ String.valueOf(documentSnapshot.getData()));
                                    }

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "onFailure:" + e.getMessage());
                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        // product
        Spinner spinner2 = findViewById(R.id.spinner2);

        List<String> productList = new ArrayList<>();
        ArrayAdapter<String> productArrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                productList
        );

        firestore.collection("product").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documents, @Nullable FirebaseFirestoreException error) {
                productList.clear();
                List<DocumentSnapshot> documentSnapshotList = documents.getDocuments();
                for(DocumentSnapshot doc: documentSnapshotList){
                    productList.add(String.valueOf(doc.get("name")));
                }
                productArrayAdapter.notifyDataSetChanged();
            }
        });
        spinner2.setAdapter(productArrayAdapter);

        // search by product
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String product = String.valueOf(spinner2.getSelectedItem());
                firestore.collection("product")
                        .where(Filter.equalTo("name",product))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();
                                for(DocumentSnapshot doc:documentSnapshotList){
                                    Log.e("TAG", "success:"+ String.valueOf(doc.getData()));
                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);



        recyclerView.setLayoutManager();
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });


    }
}