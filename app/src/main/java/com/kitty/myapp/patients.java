package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class patients extends AppCompatActivity {
    Button submit, clear;
    EditText name, age, hospnum, date,type;
    RadioButton male, female, others;
    RadioGroup gender;
    ToggleButton insurance, hlm;
    TextView insurance1, hlm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patients);
        gender = findViewById(R.id.gender);
        name = findViewById(R.id.txtSub);
        age = findViewById(R.id.editTextNumberSigned2);
        hospnum = findViewById(R.id.txthospnum);
        date = findViewById(R.id.editTextDate2);
        insurance = findViewById(R.id.toggleButton);
        hlm = findViewById(R.id.toggleButton2);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        others = findViewById(R.id.radioothers);
        insurance1 = findViewById(R.id.textView8);
        hlm1 = findViewById(R.id.textView9);
        type = findViewById(R.id.editTextNumberSigned12);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if (globalVariable.getUser() == 2)
            user = "data2";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("name");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    name.setHint(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "name not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("age");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    age.setHint(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "age not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hospnum");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    hospnum.setHint(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "hospnum not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("date");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    date.setHint(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "date not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("gender");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!(value.equals("null"))) {
                    switch (value) {
                        case "male":
                            male.setText("Male(S)");
                            female.setText("Female");
                            others.setText("Others");
                            break;
                        case "female":
                            male.setText("Male");
                            female.setText("Female(S)");
                            others.setText("Others");
                            break;
                        case "others":
                            male.setText("Male");
                            female.setText("Female");
                            others.setText("Others(S)");
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "gender not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("insurance");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if (Objects.equals(value, "YES"))
                        insurance1.setText("Insurance(Yes)");
                if (Objects.equals(value, "NO"))
                    insurance1.setText("Insurance(No)");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "insurance not found", Toast.LENGTH_SHORT).show();
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
                        hlm1.setText("HLM(Centri)");
                    else
                        hlm1.setText("HLM(Roller)");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "hlm not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hlmtype");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    type.setHint(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(patients.this, "date not found", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
            value = name.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("name").setValue(value);
            value = age.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("age").setValue(value);
            if (male.isChecked()) {
                rootRef12.child("gender").setValue("male");
                female.setChecked(false);
                others.setChecked(false);
            } else if (female.isChecked()) {
                rootRef12.child("gender").setValue("female");
                male.setChecked(false);
                female.setChecked(false);
            } else if (others.isChecked()) {
                rootRef12.child("gender").setValue("others");
                male.setChecked(false);
                female.setChecked(false);
            } else {
                Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            }
            value = hospnum.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("hospnum").setValue(value);
            value = date.getText().toString();
            if (!(value.equals("")))
                rootRef12.child("date").setValue(value);
            value = insurance.getText().toString();
            rootRef12.child("insurance").setValue(value);
            value = hlm.getText().toString();
            rootRef12.child("hlm").setValue(value);
            value = type.getText().toString();
            rootRef12.child("hlmtype").setValue(value);
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
            rootRef1.child("name").setValue("null");
            rootRef1.child("age").setValue("null");
            rootRef1.child("gender").setValue("null");
            rootRef1.child("hospnum").setValue("null");
            rootRef1.child("date").setValue("null");
            rootRef1.child("insurance").setValue("null");
            rootRef1.child("hlm").setValue("null");
            rootRef1.child("hlmtype").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
    }

}
