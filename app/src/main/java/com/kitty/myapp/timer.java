package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class timer extends AppCompatActivity {
    EditText cpbon,cpboff,totalcpb,aoxclamp,aoxclampoff,aoctotal,tcaon,tcaoff,tcattl;
    ToggleButton blood;
    Button submit,clear;
    TextView blood1;
    String cpb, cpb1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        blood1 =  findViewById(R.id.textView81);
        cpbon = findViewById(R.id.cpbon);
        cpboff = findViewById(R.id.cpboff);
        totalcpb = findViewById(R.id.totalcpb);
        aoxclamp = findViewById(R.id.aoxclamp);
        aoxclampoff = findViewById(R.id.aoxclampoff);
        aoctotal = findViewById(R.id.aoctotal);
        tcaon = findViewById(R.id.tcaon);
        tcaoff =  findViewById(R.id.tcaoff);
        tcattl =  findViewById(R.id.tcattl);
        submit = findViewById(R.id.subp);
        clear = findViewById(R.id.clearp);
        blood = findViewById(R.id.toggleButton23);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("blood");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if(Objects.equals(value, "YES"))
                        blood1.setText("Blood Added(Yes)");
                if(Objects.equals(value, "NO"))
                    blood1.setText("Blood Added(No)");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "Blood details not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("cpbon");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null")){
                    cpbon.setHint(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "CPB ON not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("cpboff");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);
                if (!Objects.equals(value, "null")){
                    cpboff.setHint(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "CBP OFF not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("totalcpb");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    totalcpb.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "Total CPB not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("aoxclamp");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    aoxclamp.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "Ao X ClampON not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("aoxclampoff");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    aoxclampoff.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "Ao.X Clamp off not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("aoxtotal");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    aoctotal.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "Ao. Occlusion Time not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("tcaon");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    tcaon.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "TCA ON not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("tcaoff");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    tcaoff.setHint(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "TCA OFF not found", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if(globalVariable12.getUser()==2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
            String value = "null";
            value = blood.getText().toString();
            rootRef12.child("blood").setValue(value);
            value = cpbon.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("cpbon").setValue(value);
                cpb = value;
            }
            value = "null";
            value = cpboff.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("cpboff").setValue(value);
                cpb1 = value;
            }
            value = "null";
            value = totalcpb.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("totalcpb").setValue(value);
            else{
                int val = Integer.parseInt(cpb) + Integer.parseInt(cpb1);
                value = String.valueOf(val);
                rootRef12.child("totalcpb").setValue(value);
                cpb = "0";
                cpb1 = "0";

            }
            value = "null";
            value = aoxclamp.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("aoxclamp").setValue(value);
                cpb = value;
            }
            value = "null";
            value = aoxclampoff.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("aoxclampoff").setValue(value);
                cpb1 = value;
            }
            value = "null";
            value = aoctotal.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("aoctotal").setValue(value);
            else{
                int val = Integer.parseInt(cpb) + Integer.parseInt(cpb1);
                value = String.valueOf(val);
                rootRef12.child("aoctotal").setValue(value);
                cpb = "0";
                cpb1 = "0";
            }
            value = "null";
            value = tcaon.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("tcaon").setValue(value);
                cpb1 = value;
            }
            value = "null";
            value = tcaoff.getText().toString();
            if(!(value.equals(""))) {
                rootRef12.child("tcaoff").setValue(value);
                cpb = value;
            }
            value = "null";
            value = tcattl.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("tcattl").setValue(value);
            }
            else{
                int val = Integer.parseInt(cpb) + Integer.parseInt(cpb1);
                value = String.valueOf(val);
                rootRef12.child("totalcpb").setValue(value);
                cpb = "0";
                cpb1 = "0";
            }
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
            rootRef1.child("blood").setValue("null");
            rootRef1.child("cpbon").setValue("null");
            rootRef1.child("cpboff").setValue("null");
            rootRef1.child("totalcpb").setValue("null");
            rootRef1.child("aoxclamp").setValue("null");
            rootRef1.child("aoxclampoff").setValue("null");
            rootRef1.child("aoctotal").setValue("null");
            rootRef1.child("tcaon").setValue("null");
            rootRef1.child("tcaoff").setValue("null");
            rootRef1.child("tcattl").setValue("null");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        totalcpb.setOnClickListener(v -> {
            String time1 = cpbon.getText().toString();
            String time2 = cpboff.getText().toString();
            String[] out1 = time1.split(" ");
            String[] out = out1[0].split(":");
            int min1,hr1,thr,tmin;
            min1 = Integer.parseInt(out[1]);
            hr1 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr1 += 12;
            out1 = time2.split(" ");
            out = out1[0].split(":");
            int min2,hr2;
            min2 = Integer.parseInt(out[1]);
            hr2 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr2 += 12;
            thr = hr2 - hr1;
            tmin = min2 - min1;
                if(tmin<0) {
                    tmin += 60;
                    thr--;
                }
            totalcpb.setText(thr +"Hrs "+ tmin +"Mins");
        });
        totalcpb.setOnClickListener(v -> {
            String time1 = cpbon.getText().toString();
            String time2 = cpboff.getText().toString();
            String[] out1 = time1.split(" ");
            String[] out = out1[0].split(":");
            int min1,hr1,thr,tmin;
            min1 = Integer.parseInt(out[1]);
            hr1 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr1 += 12;
            out1 = time2.split(" ");
            out = out1[0].split(":");
            int min2,hr2;
            min2 = Integer.parseInt(out[1]);
            hr2 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr2 += 12;
            thr = Math.abs(hr2 - hr1);
            tmin = min2 - min1;
            if(tmin<0) {
                tmin += 60;
                thr--;
            }
            totalcpb.setText(thr +"Hrs "+ tmin +"Mins");
        });
        aoctotal.setOnClickListener(v -> {
            String time1 = aoxclamp.getText().toString();
            String time2 = aoxclampoff.getText().toString();
            String[] out1 = time1.split(" ");
            String[] out = out1[0].split(":");
            int min1,hr1,thr,tmin;
            min1 = Integer.parseInt(out[1]);
            hr1 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr1 += 12;
            out1 = time2.split(" ");
            out = out1[0].split(":");
            int min2,hr2;
            min2 = Integer.parseInt(out[1]);
            hr2 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr2 += 12;
            thr = Math.abs(hr2 - hr1);
            tmin = min2 - min1;
            if(tmin<0) {
                tmin += 60;
                thr--;
            }
            aoctotal.setText(thr +"Hrs "+ tmin +"Mins");
        });
        tcattl.setOnClickListener(v -> {
            String time1 = tcaon.getText().toString();
            String time2 = tcaoff.getText().toString();
            String[] out1 = time1.split(" ");
            String[] out = out1[0].split(":");
            int min1,hr1,thr,tmin;
            min1 = Integer.parseInt(out[1]);
            hr1 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr1 += 12;
            out1 = time2.split(" ");
            out = out1[0].split(":");
            int min2,hr2;
            min2 = Integer.parseInt(out[1]);
            hr2 = Integer.parseInt(out[0]);
            if(out1[1].equals("PM"))
                hr2 += 12;
            thr = Math.abs(hr2 - hr1);
            tmin = min2 - min1;
            if(tmin<0) {
                tmin += 60;
                thr--;
            }
            tcattl.setText(thr +"Hrs "+ tmin +"Mins");
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showTimePickerDialogon(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    cpbon.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
    public void showTimePickerDialogoff(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    cpboff.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
    public void showTimePickerDialogon1(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    aoxclamp.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
    public void showTimePickerDialogoff1(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    aoxclampoff.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
    public void showTimePickerDialogon2(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    tcaon.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
    public void showTimePickerDialogoff2(View view) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(timer.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour),tm = "AM";
                    if (sHour - 12 > 0) {
                        tm = "PM";
                        sHour1 = String.valueOf(sHour - 12);
                    }
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    tcaoff.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }

}
