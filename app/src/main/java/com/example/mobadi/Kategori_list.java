package com.example.mobadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobadi.Model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Kategori_list extends AppCompatActivity {
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori_list);



        mrecyclerView=findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Menu");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getUrunKategori());
                         }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                String mTitle = mTitleTv.getText().toString();

                                Intent intent = new Intent(view.getContext(), Urun_list.class);
                                intent.putExtra("urunKategori",mTitle);
                                startActivity(intent);



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
