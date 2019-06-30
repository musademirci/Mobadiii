package com.example.mobadi.listeleme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobadi.Model.Model2;
import com.example.mobadi.R;
import com.example.mobadi.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Masalistele extends AppCompatActivity {
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    private SharedPreferences preferance;

    private  SharedPreferences.Editor preferanceEditor;



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
        FirebaseRecyclerAdapter<Model2, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model2, ViewHolder>(
                        Model2.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model2 model2, int position) {
                        viewHolder.setDetails(getApplicationContext(),model2.getAdi());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                String mTitle = mTitleTv.getText().toString();

                                preferance = getSharedPreferences("masa",MODE_PRIVATE);
                                preferanceEditor = preferance.edit();

                                preferanceEditor.putString("masaadi",mTitle);
                                preferanceEditor.commit();

                                Intent intocan = new Intent(Masalistele.this, Kategori_list.class);
                                startActivity(intocan);

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
