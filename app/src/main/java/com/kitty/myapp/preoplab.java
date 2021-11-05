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

public class preoplab extends AppCompatActivity {
    EditText hb,urea,screat,srbill,rbs,bloodgroup,circhb,others;
    Button submit,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preoplab);
        hb = findViewById(R.id.hb);
        urea = findViewById(R.id.urea);
        screat = findViewById(R.id.screat);
        srbill = findViewById(R.id.srbill);
        rbs = findViewById(R.id.rbs);
        bloodgroup = findViewById(R.id.bloodgroup);
        circhb = findViewById(R.id.circhb);
        others =  findViewById(R.id.others);
        submit = findViewById(R.id.subp);
        clear = findViewById(R.id.clearp);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hb");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    hb.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Hb not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("urea");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                urea.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "urea not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("screat");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    screat.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Screat not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("srbill");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    srbill.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "SrBill not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("rbs");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    rbs.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Rbs not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("bloodgroup");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    bloodgroup.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Blood Group not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("circhb");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    circhb.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Circ Hb not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("others");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    others.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(preoplab.this, "Others not found", Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if(globalVariable12.getUser()==2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";  value = hb.getText().toString();
            if(!(value.equals("")))
            rootRef12.child("hb").setValue(value);
            value = "null";
            value = urea.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("urea").setValue(value);
            value = "null";
            value = screat.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("screat").setValue(value);
            value = "null";
            value = srbill.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("srbill").setValue(value);
            value = "null";
            value = rbs.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("rbs").setValue(value);
            value = "null";
            value = bloodgroup.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("bloodgroup").setValue(value);
            value = "null";
            value = circhb.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("circhb").setValue(value);
            value = "null";
            value = others.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("others").setValue(value);
            value = "null";
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
            rootRef1.child("hb").setValue("null");
            rootRef1.child("urea").setValue("null");
            rootRef1.child("screat").setValue("null");
            rootRef1.child("srbill").setValue("null");
            rootRef1.child("rbs").setValue("null");
            rootRef1.child("bloodgroup").setValue("null");
            rootRef1.child("circhb").setValue("null");
            rootRef1.child("others").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

    }

}
