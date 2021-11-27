package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    Button add, remove, select;
    EditText cpg;
    TableLayout table;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        add = findViewById(R.id.add);
        cpg = findViewById(R.id.cpg);
        table = findViewById(R.id.table);
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
        remove = findViewById(R.id.cleard);
        select = findViewById(R.id.selectmode);
        cpbon.addTextChangedListener(textWatcher);
        aoxclamp.addTextChangedListener(textWatcher1);
        tcaon.addTextChangedListener(textWatcher2);
        cpboff.addTextChangedListener(textWatcher);
        aoxclampoff.addTextChangedListener(textWatcher1);
        tcaoff.addTextChangedListener(textWatcher2);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if (globalVariable.getUser() == 2)
            user = "data2";
        DatabaseReference rootRef;
        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("bloodcount");
        rootRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                Global globalVariable = (Global) getApplicationContext();
                String user = "data1";
                if (globalVariable.getUser() == 2)
                    user = "data2";
                DatabaseReference rootRef1;
                for (int i = 0; i < Integer.parseInt(value) + 1; i++) {
                    TableRow row = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    TextView time = new TextView(getApplicationContext());
                    TextView qty = new TextView(getApplicationContext());
                    String a = Integer.toString(i);
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("bloodtable").child(a).child("time");
                    rootRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = "null";
                            value = dataSnapshot.getValue(String.class);

                            if (!Objects.equals(value, "null")) {
                                time.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(timer.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                    });
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("bloodtable").child(a).child("unit");
                    rootRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = "null";
                            value = dataSnapshot.getValue(String.class);

                            if (!Objects.equals(value, "null")) {
                                qty.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(timer.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                    });
                    time.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                    time.setGravity(Gravity.CENTER);
                    qty.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                    qty.setGravity(Gravity.CENTER);
                    row.addView(time);
                    row.addView(qty);
                    table.addView(row);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(timer.this, "count not found", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12).child("bloodcount");
            rootRef12.get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    String a = String.valueOf(task.getResult().getValue());
                    Global globalVariable121 = (Global) getApplicationContext();
                    String usera = "data1";
                    if (globalVariable121.getUser() == 2)
                        usera = "data2";
                    DatabaseReference rootRef121 = FirebaseDatabase.getInstance().getReference().child(usera).child("bloodcount");
                    String value = select.getText().toString();
                    String value1 = cpg.getText().toString();
                    DatabaseReference rootRefa = FirebaseDatabase.getInstance().getReference().child(usera).child("bloodtable");
                    if ((!(value.equals("Select time"))) && (!(value1.equals("")))) {
                        table.invalidate();
                        table.removeViews(0, table.getChildCount());
                        a = Integer.toString(Integer.parseInt(a) + 1);
                        rootRef121.setValue(a);
                        value = value.replace(":", "-");
                        rootRefa.child(a).child("time").setValue(value);
                        rootRefa.child(a).child("unit").setValue(value1);
                    }
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });

        });
        remove.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            DatabaseReference rootRefa = FirebaseDatabase.getInstance().getReference().child(user12).child("bloodtable");
            rootRefa.getRef().removeValue();
            rootRefa = FirebaseDatabase.getInstance().getReference().child(user12).child("bloodcount");
            rootRefa.setValue("0");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

        globalVariable = (Global) getApplicationContext();
        user = "data1";
        if(globalVariable.getUser()==2)
            user = "data2";

        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("blood");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";  value = dataSnapshot.getValue(String.class);

                if (!Objects.equals(value, "null"))
                    if(Objects.equals(value, "YES")){
                        blood.setText("Blood Added(Yes)");
                         blood.setChecked(true);
                    }
                if(Objects.equals(value, "NO")) {
                    blood.setText("Blood Added(No)");
                    blood.setChecked(false);
                }
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
                    cpbon.setText(value);
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
                    cpboff.setText(value);
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
                    totalcpb.setText(value);
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
                    aoxclamp.setText(value);
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
                    aoxclampoff.setText(value);
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
                    aoctotal.setText(value);
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
                    tcaon.setText(value);
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
                    tcaoff.setText(value);
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
            }
            value = "null";
            value = cpboff.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("cpboff").setValue(value);
            }
            value = "null";
            value = totalcpb.getText().toString();
            if(!(value.equals("")))
                rootRef12.child("totalcpb").setValue(value);
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
            DatabaseReference rootRef3 = FirebaseDatabase.getInstance().getReference().child(user1);
            rootRef3.child("blood").setValue("null");
            rootRef3.child("cpbon").setValue("null");
            rootRef3.child("cpboff").setValue("null");
            rootRef3.child("totalcpb").setValue("null");
            rootRef3.child("aoxclamp").setValue("null");
            rootRef3.child("aoxclampoff").setValue("null");
            rootRef3.child("aoctotal").setValue("null");
            rootRef3.child("tcaon").setValue("null");
            rootRef3.child("tcaoff").setValue("null");
            rootRef3.child("tcattl").setValue("null");
            rootRef3.child("bloodcount").setValue("0");
            rootRef3.child("bloodtable").removeValue();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        totalcpb.setOnClickListener(v -> {
            String time1 = cpbon.getText().toString();
            String time2 = cpboff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = hr2 - hr1;
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                totalcpb.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                totalcpb.setText("");
        });
        aoctotal.setOnClickListener(v -> {
            String time1 = aoxclamp.getText().toString();
            String time2 = aoxclampoff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = Math.abs(hr2 - hr1);
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                aoctotal.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                aoctotal.setText("");
        });
        tcattl.setOnClickListener(v -> {
            String time1 = tcaon.getText().toString();
            String time2 = tcaoff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = Math.abs(hr2 - hr1);
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                tcattl.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                tcattl.setText("");
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
            String time1 = cpbon.getText().toString();
            String time2 = cpboff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = hr2 - hr1;
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                totalcpb.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                totalcpb.setText("");

        }
    };
    TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            String time1 = aoxclamp.getText().toString();
            String time2 = aoxclampoff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = Math.abs(hr2 - hr1);
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                aoctotal.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                aoctotal.setText("");
        }
    };
    TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            String time1 = tcaon.getText().toString();
            String time2 = tcaoff.getText().toString();
            if (!time1.equals("")&&!time2.equals("")) {
                String[] out1 = time1.split(" ");
                String[] out = out1[0].split(":");
                int min1, hr1, thr, tmin;
                min1 = Integer.parseInt(out[1]);
                hr1 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr1 += 12;
                out1 = time2.split(" ");
                out = out1[0].split(":");
                int min2, hr2;
                min2 = Integer.parseInt(out[1]);
                hr2 = Integer.parseInt(out[0]);
                if (out1[1].equals("PM"))
                    hr2 += 12;
                thr = Math.abs(hr2 - hr1);
                tmin = min2 - min1;
                if (tmin < 0) {
                    tmin += 60;
                    thr--;
                }
                tcattl.setText(thr + "Hrs " + tmin + "Mins");
            }
            else
                tcattl.setText("");
        }
    };
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
    public void showTimePickerDialog(View view) {
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
                    select.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }

}
