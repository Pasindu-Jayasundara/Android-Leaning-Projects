package com.example.app17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

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

        Button btn1 = findViewById(R.id.buttonCreateNew);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(intent);

            }
        });
    }
}

class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titleView;
        TextView contentView;
        TextView date_createdView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.textViewTitle);
            contentView = itemView.findViewById(R.id.textViewContent);
            date_createdView = itemView.findViewById(R.id.textViewDate);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(inflatedView);

        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

}