package com.example.app17;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app17.model.SQLiteHelper;
import com.google.gson.Gson;

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

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
            new ItemTouchHelper.Callback(){

                @Override
                public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                    return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
                }

                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    Log.i("NotebookLog","Swiped");

                    SQLiteHelper sqLiteHelper = new SQLiteHelper(viewHolder.itemView.getContext(),"mynotebook.db",null,1);
                    NoteListAdapter.NoteViewHolder holder = (NoteListAdapter.NoteViewHolder) viewHolder;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();

                            int deletedRows = writableDatabase.delete(
                                    "notes",
                                    "`id`=?",
                                    new String[]{holder.id}
                            );
                            Log.i("NotebookLog","Note Deleted, Count: "+String.valueOf(deletedRows));

                        }
                    }).start();

                }
            }
        );
        itemTouchHelper.attachToRecyclerView(recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(MainActivity.this,"mynotebook.db",null,1);

        new Thread(new Runnable() {
            @Override
            public void run() {

                SQLiteDatabase sqLiteDatabase = sqLiteHelper.getReadableDatabase();
                Cursor cursor = sqLiteDatabase.query(
                        "notes",
                        null,
                        null,
                        null,
                        null,
                        null,
                        "`id` DESC"
                );

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NoteListAdapter noteListAdapter = new NoteListAdapter(cursor);
                        recyclerView.setAdapter(noteListAdapter);
                    }
                });

            }
        }).start();
    }
}

class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    Cursor cursor;

    public NoteListAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        String id;
        TextView titleView;
        TextView contentView;
        TextView date_createdView;
        View containerView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.textViewTitle);
            contentView = itemView.findViewById(R.id.textViewContent);
            date_createdView = itemView.findViewById(R.id.textViewDate);
            containerView = itemView;
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
        cursor.moveToPosition(position);

        holder.id = cursor.getString(0);
        String title = cursor.getString(1);
        String content = cursor.getString(2);
        String dateCreated = cursor.getString(3);

        holder.titleView.setText(title);
        holder.contentView.setText(content);
        holder.date_createdView.setText(dateCreated);

        holder.containerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(),CreateNoteActivity.class);
                i.putExtra("id",holder.id);
                i.putExtra("title",title);
                i.putExtra("content",content);

                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

}