package com.kitty.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class prime extends AppCompatActivity {
    EditText crystalloid,colloid,mannitol,hco3,heparin,totalprime;
    Button submit,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prime);
        crystalloid =  findViewById(R.id.crystalloid);
        colloid = findViewById(R.id.colloid);
        mannitol = findViewById(R.id.mannitol);
        hco3 = findViewById(R.id.hco3);
        heparin = findViewById(R.id.heparin);
        totalprime = findViewById(R.id.totalprime);
        submit = findViewById(R.id.subpr);
        clear = findViewById(R.id.clearpr);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("crystalloid");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null")){
                    crystalloid.setHint(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "Crystalloid not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("colloid");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null")){
                    colloid.setHint(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "Colloid not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("mannitol");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    mannitol.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "Mannitol not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hco3");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    hco3.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "HCo3 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("heparin");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    heparin.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalprime");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    totalprime.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prime.this, "Total Prime not found", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(v -> {
            int val=0;
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if(globalVariable12.getUser()==2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
            value = crystalloid.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("crystalloid").setValue(value);
                val += Integer.parseInt(value);
            }
            value = "null";
            value = colloid.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("colloid").setValue(value);
                val += Integer.parseInt(value);
            }
            value = "null";
            value = mannitol.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("mannitol").setValue(value);
                val += Integer.parseInt(value);
            }
            value = "null";
            value = hco3.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("hco3").setValue(value);
                val += Integer.parseInt(value);
            }
            value = "null";
            value = heparin.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("heparin").setValue(value);
                val += Integer.parseInt(value);
            }
            value = "null";
            value = totalprime.getText().toString();
            if(!(value.equals(""))) {
                rootRef12.child("totalprime").setValue(value);
            }
            else{
                value = String.valueOf(val);
                rootRef12.child("totalprime").setValue(value);
            }
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        clear.setOnClickListener(v -> {
            Global globalVariable1 = (Global) getApplicationContext();
            String user1 = "data1";
            if(globalVariable1.getUser()==2)
                user1 = "data2";
            DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user1);
            rootRef1.child("crystalloid").setValue("null");
            rootRef1.child("colloid").setValue("null");
            rootRef1.child("mannitol").setValue("null");
            rootRef1.child("hco3").setValue("null");
            rootRef1.child("heparin").setValue("null");
            rootRef1.child("totalprime").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

    }

}
