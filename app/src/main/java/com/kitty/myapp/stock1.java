package com.kitty.myapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class stock1 extends AppCompatActivity {
    listsAdapter1 lists; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock);
        mbase = FirebaseDatabase.getInstance().getReference("temp1");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<lists> options
                = new FirebaseRecyclerOptions.Builder<lists>()
                .setQuery(mbase, lists.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        lists = new listsAdapter1(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(lists);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        lists.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        lists.stopListening();
    }

}
