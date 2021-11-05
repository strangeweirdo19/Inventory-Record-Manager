package com.kitty.myapp;

import static java.lang.Math.abs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class fluidmenu extends AppCompatActivity {
    EditText prime, blood, fluids, diuretics, hco3, cpg, drugs,  pump, surgicalloss, urineoutput, cuf, muf;
    TextView totalgain,totalloss,totalfluid;
    ToggleButton lossgain;
    Button submite, clearw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid);
        submite = findViewById(R.id.subfr);
        clearw = findViewById(R.id.clearff);
        prime = findViewById(R.id.prime2);
        blood = findViewById(R.id.blood);
        fluids = findViewById(R.id.fluids);
        diuretics = findViewById(R.id.diuretics);
        hco3 = findViewById(R.id.hco32);
        cpg = findViewById(R.id.cpg2);
        drugs = findViewById(R.id.drugs);
        totalgain = findViewById(R.id.totalgain);
        pump = findViewById(R.id.pump);
        surgicalloss = findViewById(R.id.surgicalloss);
        urineoutput = findViewById(R.id.urineoutput);
        cuf = findViewById(R.id.cuf);
        muf = findViewById(R.id.muf);
        totalloss = findViewById(R.id.totalloss);
        totalfluid = findViewById(R.id.fluid);
        lossgain = findViewById(R.id.toggleButton3);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";
        DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalprime");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    prime.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("pumpblood");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    blood.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("pumpfluids");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    fluids.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("diuretics");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    diuretics.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("hco3");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    hco3.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("cpg");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    cpg.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("drugs");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    drugs.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalgain");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    totalgain.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("pump");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    pump.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("surgicalloss");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    surgicalloss.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("urineoutput");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    urineoutput.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("cuf");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    cuf.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("muf");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    muf.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalloss");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    totalloss.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalfluid");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    totalfluid.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("lossgain");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null"))
                    if(value.equals("Loss"))
                        lossgain.setChecked(false);
                    else if(value.equals("Gain"))
                        lossgain.setChecked(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fluidmenu.this, "Heparin not found", Toast.LENGTH_SHORT).show();
            }
        });
        totalgain.setOnClickListener(v -> {
            int tgain = Integer.parseInt(prime.getText().toString());
            tgain += Integer.parseInt(blood.getText().toString());
            tgain += Integer.parseInt(fluids.getText().toString());
            tgain += Integer.parseInt(diuretics.getText().toString());
            tgain += Integer.parseInt(hco3.getText().toString());
            tgain += Integer.parseInt(cpg.getText().toString());
            tgain += Integer.parseInt(drugs.getText().toString());
            if(tgain>0)
            totalgain.setText(String.valueOf(tgain));

        });
        totalloss.setOnClickListener(v -> {
            int tgain = Integer.parseInt(pump.getText().toString());
            tgain += Integer.parseInt(surgicalloss.getText().toString());
            tgain += Integer.parseInt(urineoutput.getText().toString());
            tgain += Integer.parseInt(cuf.getText().toString());
            tgain += Integer.parseInt(muf.getText().toString());
            if(tgain>0)
            totalloss.setText(String.valueOf(tgain));

        });
        totalfluid.setOnClickListener(v -> {
            int tgain = Integer.parseInt(totalgain.getText().toString());
            tgain -= Integer.parseInt(totalloss.getText().toString());
            if(tgain!=0)
            totalfluid.setText(String.valueOf(abs(tgain)));
            if(tgain>=0)
                lossgain.setChecked(true);
            if(tgain<0)
                lossgain.setChecked(false);
        });
        submite.setOnClickListener(v -> {
            Global globalVariable12 = (Global) fluidmenu.this.getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            String value = prime.getText().toString();
            DatabaseReference rootRef12;
            rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            rootRef12.child("prime").setValue(value);
            value = "null";
            value = blood.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("pumpblood").setValue(value);
            }
            value = "null";
            value = fluids.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("pumpfluids").setValue(value);
            }
            value = "null";
            value = diuretics.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("diuretics").setValue(value);
            }
            value = "null";
            value = hco3.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("hco3").setValue(value);
            }
            value = "null";
            value = cpg.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("cpg").setValue(value);
            }
            value = "null";
            value = drugs.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("drugs").setValue(value);
            }
            value = "null";
            value = totalgain.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("totalgain").setValue(value);
            }
            value = "null";
            value = pump.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("pump").setValue(value);
            }
            value = "null";
            value = surgicalloss.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("surgicalloss").setValue(value);
            }
            value = "null";
            value = urineoutput.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("urineoutput").setValue(value);
            }
            value = "null";
            value = cuf.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("cuf").setValue(value);
            }
            value = "null";
            value = muf.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("muf").setValue(value);
            }
            value = "null";
            value = lossgain.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("lossgain").setValue(value);
            }
            value = "null";
            value = totalfluid.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("totalfluid").setValue(value);
            }
            value = "null";
            value = totalloss.getText().toString();
            if (!(value.equals(""))) {
                rootRef12.child("totalloss").setValue(value);
            }
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        clearw.setOnClickListener(v -> {
            Global globalVariable1 = (Global) getApplicationContext();
            String user1 = "data1";
            if(globalVariable1.getUser()==2)
                user1 = "data1";
            DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user1);
            rootRef1.child("prime").setValue("null");
            rootRef1.child("pumpblood").setValue("null");
            rootRef1.child("diuretics").setValue("null");
            rootRef1.child("hco3").setValue("null");
            rootRef1.child("cpg").setValue("null");
            rootRef1.child("drugs").setValue("null");
            rootRef1.child("totalgain").setValue("null");
            rootRef1.child("pump").setValue("null");
            rootRef1.child("surgicalloss").setValue("null");
            rootRef1.child("urineoutput").setValue("null");
            rootRef1.child("cuf").setValue("null");
            rootRef1.child("muf").setValue("null");
            rootRef1.child("totalfluid").setValue("null");
            rootRef1.child("totalloss").setValue("null");
            rootRef1.child("lossgain").setValue("null");
            rootRef1.child("pumpfluids").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
    }
}
