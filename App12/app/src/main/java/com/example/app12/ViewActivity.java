package com.example.app12;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app12.model.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sp = getSharedPreferences("com.sample.app12.data.contacts", Context.MODE_PRIVATE);
        String contactJson = sp.getString("contactsJson", null);

        if(contactJson == null){
            Toast.makeText(this,"Contact List Not Found",Toast.LENGTH_LONG).show();
        }else{
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Contact>>(){}.getType();
            ArrayList<Contact> contactArrayList = gson.fromJson(contactJson, type);

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(new Contactdapter(contactArrayList));
        }
    }
}

class Contactdapter extends RecyclerView.Adapter<ContactViewHolder>{

    ArrayList<Contact> contactArrayList;

    public Contactdapter(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View contactView = layoutInflater.inflate(R.layout.contact_item,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(contactView);

        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }
}

class ContactViewHolder extends RecyclerView.ViewHolder{

    TextView textViewLetter;
    TextView textViewName;
    TextView textViewMobileCity;

    Button buttonCall;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewLetter = itemView.findViewById(R.id.textViewLetter);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewMobileCity = itemView.findViewById(R.id.textViewMobileCity);
        buttonCall = itemView.findViewById(R.id.buttonCall);
    }
}