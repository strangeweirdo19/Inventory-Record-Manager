package com.kitty.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ordersAdapter extends FirebaseRecyclerAdapter<
        orders, ordersAdapter.ordersViewholder> {


    public ordersAdapter(
            @NonNull FirebaseRecyclerOptions<orders> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") with data in
    // model class(here "orders.class")
    @Override
    protected void
    onBindViewHolder(@NonNull ordersViewholder holder,
                     int position, @NonNull orders model)
    {
        String quantity,title,type;
        quantity = model.getQuantity();
        type = model.getType();
        title = model.getTitle();
        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.title.setText(title);

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.type.setText(type);

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.quantity.setText(quantity);

        holder.add.setOnClickListener(v -> {
           DatabaseReference update = FirebaseDatabase.getInstance().getReference("temp1").child(title+" "+type).child("quantity");
           String qnty = String.valueOf((Integer.parseInt(quantity) + 1));
           if (qnty.equals("4")){
               Toast.makeText(v.getContext(),
                       "Permission denied contact Admin To add!",
                       Toast.LENGTH_LONG)
                       .show();
           }
           else
           update.setValue(qnty);
        });
        holder.del.setOnClickListener(v -> {
            DatabaseReference update = FirebaseDatabase.getInstance().getReference().child("temp1").child(title+" "+type);
            update.removeValue();
        });
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public ordersViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders, parent, false);
        return new ordersViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    static class ordersViewholder
            extends RecyclerView.ViewHolder {
        TextView title, type, quantity;
        Button add,del;
        public ordersViewholder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            quantity = itemView.findViewById(R.id.quantity);
            add = itemView.findViewById(R.id.add);
            del = itemView.findViewById(R.id.button2);
        }
    }
}

