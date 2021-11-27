package com.kitty.myapp;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.N)
public class diagnosis extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    Button submit,clear;
    EditText preopdiag,procedure,height,weight,zerofive,one,onefive,oneeight,two,twotwo,twofour,surgeon,perfusionist,anaesthesiologist;
    TextView flow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis);
        flow = findViewById(R.id.flo);
        submit = findViewById(R.id.subd);
        preopdiag = findViewById(R.id.preopdiag);
        procedure = findViewById(R.id.procedure);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        zerofive = findViewById(R.id.fivetable);
        one = findViewById(R.id.onetable);
        onefive = findViewById(R.id.onefivetable);
        oneeight = findViewById(R.id.oneeighttable);
        two = findViewById(R.id.twotable);
        twotwo = findViewById(R.id.twotwotable);
        twofour = findViewById(R.id.twofourtable);
        surgeon = findViewById(R.id.surgeon);
        perfusionist = findViewById(R.id.perfusionist);
        anaesthesiologist = findViewById(R.id.anslogist);
        clear = findViewById(R.id.cleard);
        height.addTextChangedListener(textWatcher);
        weight.addTextChangedListener(textWatcher);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("preopdiag");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    preopdiag.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "preopdiag not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("procedure");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    procedure.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "procedure not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("height");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null")){
                    height.setText(value);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "height not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("weight");
        rootRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null")) {
                    weight.setText(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "weight not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("zerofive");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    zerofive.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "0.5 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("one");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    one.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "preopdiag not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("onefive");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    onefive.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "1.5 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("oneeight");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    oneeight.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "1.8 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("two");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    two.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "2 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("twotwo");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    twotwo.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "2.2 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("twofour");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    twofour.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "2.4 not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("surgeon");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    surgeon.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "surgeon not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("perfusionist");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    perfusionist.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "perfusionist not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("anaesthesiologist");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    anaesthesiologist.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(diagnosis.this, "Anaesthesiologist not found", Toast.LENGTH_SHORT).show();
            }
        });
        flow.setOnClickListener(v -> {
            double ht = 0,wt=0,bsa;
            String value1 = height.getText().toString();
            if(!(value1.equals("")))
                ht = Double.parseDouble(value1);
            value1 = weight.getText().toString();
            if(!(value1.equals("")))
                wt = Double.parseDouble(value1);
            bsa = (ht*wt/3600);
            if((bsa!=0)&&(bsa!=1)){
                zerofive.setText(df2.format(bsa*0.5));
                one.setText(df2.format(bsa));
                onefive.setText(df2.format(bsa*1.5));
                oneeight.setText(df2.format(bsa*1.8));
                two.setText(df2.format(bsa*2));
                twotwo.setText(df2.format(bsa*2.2));
                twofour.setText(df2.format(bsa*2.4));
            }
        });
        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if(globalVariable12.getUser()==2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
            value = preopdiag.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("preopdiag").setValue(value);
            value = procedure.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("procedure").setValue(value);
            value = height.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("height").setValue(value);
            value = weight.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("weight").setValue(value);
            value = zerofive.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("zerofive").setValue(value);
            value = one.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("one").setValue(value);
            value = onefive.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("onefive").setValue(value);
            value = oneeight.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("oneeight").setValue(value);
            value = two.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("two").setValue(value);
            value = twotwo.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("twotwo").setValue(value);
            value = twofour.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("twofour").setValue(value);
            value = surgeon.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("surgeon").setValue(value);
            value = perfusionist.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("perfusionist").setValue(value);
            value = anaesthesiologist.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("anaesthesiologist").setValue(value);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        clear.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if(globalVariable12.getUser()==2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
                rootRef12.child("preopdiag").setValue(value);
                rootRef12.child("procedure").setValue(value);
                rootRef12.child("height").setValue(value);
                rootRef12.child("weight").setValue(value);
                rootRef12.child("zerofive").setValue(value);
                rootRef12.child("one").setValue(value);
                rootRef12.child("onefive").setValue(value);
                rootRef12.child("oneeight").setValue(value);
                rootRef12.child("two").setValue(value);
                rootRef12.child("twotwo").setValue(value);
                rootRef12.child("twofour").setValue(value);
                rootRef12.child("surgeon").setValue(value);
                rootRef12.child("perfusionist").setValue(value);
                rootRef12.child("anaesthesiologist").setValue(value);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            double ht = 0,wt=0,bsa;
            String value1 = height.getText().toString();
            if(!(value1.equals("")))
                ht = Double.parseDouble(value1);
            value1 = weight.getText().toString();
            if(!(value1.equals("")))
                wt = Double.parseDouble(value1);
            bsa = (ht*wt/3600);
            if((bsa!=0)&&(bsa!=1)){
                zerofive.setText(df2.format(bsa*0.5));
                one.setText(df2.format(bsa));
                onefive.setText(df2.format(bsa*1.5));
                oneeight.setText(df2.format(bsa*1.8));
                two.setText(df2.format(bsa*2));
                twotwo.setText(df2.format(bsa*2.2));
                twofour.setText(df2.format(bsa*2.4));
            }
        }
    };

}
