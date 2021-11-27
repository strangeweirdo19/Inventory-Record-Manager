package com.kitty.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class listsAdapter1 extends FirebaseRecyclerAdapter<
        lists, listsAdapter1.listsViewholder> {
    public listsAdapter1(
            @NonNull FirebaseRecyclerOptions<lists> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") with data in
    // model class(here "lists.class")
    @Override
    protected void
    onBindViewHolder(@NonNull listsViewholder holder,
                     int position, @NonNull lists model)
    {
        holder.add.setText("ADD");
        String title,type;
        type = model.getType();
        title = model.getTitle();
        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("stock").child(title+" "+type).child("quantity");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    holder.quantity.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        holder.title.setText(title);

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.type.setText(type);

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.add.setOnClickListener(v -> {
           DatabaseReference update = FirebaseDatabase.getInstance().getReference("stock").child(title+" "+type).child("quantity");
           String qnty = holder.count.getText().toString();
           String quantity = holder.quantity.getText().toString();
           if(!qnty.equals(""))
           update.setValue(String.valueOf(Integer.parseInt(qnty)+Integer.parseInt(quantity)));
        });

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public listsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lists, parent, false);
        return new listsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    static class listsViewholder
            extends RecyclerView.ViewHolder {
        TextView title, type, quantity;
        Button add;
        EditText count;
        public listsViewholder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            quantity = itemView.findViewById(R.id.quantity);
            add = itemView.findViewById(R.id.add);
            count = itemView.findViewById(R.id.editTextNumberSigned);
        }
    }
}

