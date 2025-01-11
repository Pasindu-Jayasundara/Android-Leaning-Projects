package com.example.app22;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app22.model.CountryData;

import java.util.ArrayList;
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

        Spinner spinner1 = findViewById(R.id.spinner1);

         String []coutries = new String[]{"Sri Lanka","Canada","Australia","India"};

        //ArrayList<String> arrayList = new ArrayList<>();
        //arrayList.add("SriLanka");

        ArrayList<CountryData> arrayList = new ArrayList<>();
        arrayList.add(new CountryData(R.drawable.ic_launcher_background,"A"));
        arrayList.add(new CountryData(R.drawable.ic_launcher_background,"A"));
        arrayList.add(new CountryData(R.drawable.ic_launcher_background,"A"));
        arrayList.add(new CountryData(R.drawable.ic_launcher_background,"A"));

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
//                MainActivity.this,
//                R.layout.custom_spinner_item,
//                arrayList
//        );

        CountryAdapter countryAdapter = new CountryAdapter(
                MainActivity.this,
                R.layout.country_spinner_item,
                arrayList
        );

        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner1.setAdapter(arrayAdapter);
        spinner1.setAdapter(countryAdapter);
    }
}

class CountryAdapter extends ArrayAdapter<CountryData>{

    List<CountryData> countryDataList;

    int layout;

    public CountryAdapter(@NonNull Context context, int resource, @NonNull List<CountryData> objects) {
        super(context, resource, objects);
        countryDataList = objects;
        layout = resource;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(layout,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        imageView.setImageResource(countryDataList.get(position).getFlagResourceId());
        textView.setText(countryDataList.get(position).getName());

        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getDropDownView(position,convertView,parent);
    }
}