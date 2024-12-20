package com.example.app11;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app11.model.User;

import java.util.ArrayList;

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

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> userList = new ArrayList<>();
                userList.add(new User("U001","Kamal","0717900130","Colombo"));
                userList.add(new User("U002","Nimal","0740211671","Kandy"));
                userList.add(new User("U003","Sunimal","0713309367","Gampaha"));
                userList.add(new User("U004","Suneth","077977299","Kurunegal"));
                userList.add(new User("U005","Nuwan","0721455878","Colombo"));

                RecyclerView recyclerView = findViewById(R.id.recyclearView1);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setAdapter(new UserAdapter(userList));

            }
        });
    }
}

//class UserViewHolder extends RecyclerView.ViewHolder{
//
//    public TextView textView1;
//    public TextView textView2;
//    public TextView textView3;
//    public Button button1;
//
//    public UserViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        textView1 = itemView.findViewById(R.id.tv1);
//        textView2 = itemView.findViewById(R.id.tv2);
//        textView3 = itemView.findViewById(R.id.tv3);
//        button1 = itemView.findViewById(R.id.b1);
//    }
//}

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    class UserViewHolder extends RecyclerView.ViewHolder{

        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public Button button1;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.tv1);
            textView2 = itemView.findViewById(R.id.tv2);
            textView3 = itemView.findViewById(R.id.tv3);
            button1 = itemView.findViewById(R.id.b1);
        }
    }

    public ArrayList<User> userArrayList;

    public UserAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflateView = layoutInflater.inflate(R.layout.user_view, parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(inflateView);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textView1.setText(this.userArrayList.get(position).getId());
        holder.textView2.setText(this.userArrayList.get(position).getName());
        holder.textView3.setText(this.userArrayList.get(position).getCity());

        String mobile = this.userArrayList.get(position).getMobile();
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        view.getContext(),
                        mobile,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.userArrayList.size();
    }
}