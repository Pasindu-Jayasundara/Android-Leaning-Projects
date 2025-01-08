package com.example.app19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View toastView = layoutInflater.inflate(R.layout.toast_error, null,false);

                Toast toast = new Toast(MainActivity.this);
                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_LONG);

                EditText titleView = findViewById(R.id.inputTextTitle);
                EditText descriptionView = findViewById(R.id.inputTextDescription);
                EditText categoryView = findViewById(R.id.inputTextCategory);
                EditText brandView = findViewById(R.id.inputTextBrand);
                EditText quantityView = findViewById(R.id.inputTextQty);
                EditText priceView = findViewById(R.id.inputTextPrice);

                String title = titleView.getText().toString();
                String description = descriptionView.getText().toString();
                String category = categoryView.getText().toString();
                String brand = brandView.getText().toString();
                String qty = quantityView.getText().toString();
                String price = priceView.getText().toString();

                if(title.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Title");
                    toast.show();
                    return;
                }

                if(description.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Description");
                    toast.show();
                    return;
                }

                if(category.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Category");
                    toast.show();
                    return;
                }

                if(brand.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Brand");
                    toast.show();
                    return;
                }

                if(qty.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Quantity");
                    toast.show();
                    return;
                }

                if(price.isEmpty()){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Fill the Product Price");
                    toast.show();
                    return;
                }

                if(Integer.parseInt(qty) < 0){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Invalid Quantity");
                    toast.show();
                    return;
                }

                if(Integer.parseInt(price) < 0){
                    ((TextView) toastView.findViewById(R.id.textView2)).setText("Invalid Price");
                    toast.show();
                    return;
                }

            }
        });
    }
}