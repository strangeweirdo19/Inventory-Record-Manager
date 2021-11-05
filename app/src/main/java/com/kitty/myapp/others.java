package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class others extends AppCompatActivity {
    Button submit, clear;
    EditText mitral, aortic, conduit , comments;
    ToggleButton shock, support,hematuria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others);
        mitral = findViewById(R.id.mitral);
        aortic = findViewById(R.id.aortic);
        conduit = findViewById(R.id.conduit);
        comments = findViewById(R.id.comments);
        shock = findViewById(R.id.shock);
        support = findViewById(R.id.supports);
        hematuria = findViewById(R.id.hematuria);
        submit = findViewById(R.id.subo);
        clear = findViewById(R.id.cleard);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if (globalVariable.getUser() == 2)
            user = "data2";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("shock");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    if(value.equals("Yes"))
                    shock.setChecked(true);
                    else
                    shock.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "name not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("support");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    if (!Objects.equals(value, "null"))
                        if(value.equals("Yes"))
                            support.setChecked(true);
                        else
                            support.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "age not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hematuria");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if(value.equals("Yes"))
                        hematuria.setChecked(true);
                    else if(value.equals("No"))
                        hematuria.setChecked(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hospnum not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("mitral");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    mitral.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "date not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hlm");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if (Objects.equals(value, "CENTRI"))
                        aortic.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hlm");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if (Objects.equals(value, "CENTRI"))
                        aortic.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("aortic");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    aortic.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("conduit");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    conduit.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("comments");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    comments.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.kitty.myapp.others.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
            value = shock.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("shock").setValue(value);
            value = support.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("support").setValue(value);
            value = hematuria.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("hematuria").setValue(value);
            value = mitral.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("mitral").setValue(value);
            value = aortic.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("aortic").setValue(value);
            value = conduit.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("conduit").setValue(value);
            value = comments.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("comments").setValue(value);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        clear.setOnClickListener(v -> {

            Global globalVariable1 = (Global) getApplicationContext();
            String user1 = "data1";
            if (globalVariable1.getUser() == 2)
                user1 = "data2";
            DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user1);
            rootRef1.child("shock").setValue("null");
            rootRef1.child("support").setValue("null");
            rootRef1.child("hematuria").setValue("null");
            rootRef1.child("mitral").setValue("null");
            rootRef1.child("aortic").setValue("null");
            rootRef1.child("conduit").setValue("null");
            rootRef1.child("comments").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

    }

}
