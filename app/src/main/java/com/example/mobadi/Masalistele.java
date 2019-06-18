package com.example.mobadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Masalistele extends AppCompatActivity {
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masalistele);



        mrecyclerView=findViewById(R.id.recyclerView2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Masalar");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Masalar,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Masalar, ViewHolder>(
                        Masalar.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Masalar masalar, int position) {
                        viewHolder.setDetails(getApplicationContext(),masalar.getMasaadi());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {


                            }
                        });
                        return viewHolder;
                    }


                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
