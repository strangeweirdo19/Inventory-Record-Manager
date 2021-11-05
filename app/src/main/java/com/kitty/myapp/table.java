package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
@SuppressWarnings("unchecked")
public class table extends AppCompatActivity {
    EditText  events, pressure, flows, fio2, temp, ph, o2, pco2, be, hb, na, k, act, rbs, lac;
    TextView time;
    Spinner sp;
    Button submit, clear,remove;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        remove = findViewById(R.id.remove);
        submit = findViewById(R.id.subd);
        clear = findViewById(R.id.cleard);
        sp = findViewById(R.id.spinner);
        time = findViewById(R.id.time);
        events = findViewById(R.id.events);
        pressure = findViewById(R.id.pressure);
        flows = findViewById(R.id.flows);
        fio2 = findViewById(R.id.fio2);
        temp = findViewById(R.id.temp);
        ph = findViewById(R.id.ph);
        o2 = findViewById(R.id.o2);
        pco2 = findViewById(R.id.pco2);
        be = findViewById(R.id.be);
        hb = findViewById(R.id.hb);
        na = findViewById(R.id.na);
        k = findViewById(R.id.k);
        act = findViewById(R.id.act);
        rbs = findViewById(R.id.rbs);
        lac = findViewById(R.id.lac);
        Global globalVariable = (Global) getApplicationContext();
        String user = "table1";
        if(globalVariable.getUser()==2)
            user = "table2";
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
        String formattedDate = df.format(c.getTime());
        time.setText(formattedDate);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("title");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                globalVariable.setStr1(value);
                String[] elements = value.split("Z");
                List<String> fixedLenghtList = Arrays.asList(elements);
                ArrayList<String> arrayList = new ArrayList<String>(fixedLenghtList);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(table.this,android.R.layout.simple_spinner_item, arrayList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(arrayAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(table.this, "times not found", Toast.LENGTH_SHORT).show();
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Global globalVariable = (Global) getApplicationContext();
                globalVariable.setStr(selected);
                String user = "table1";
                if(globalVariable.getUser()==2)
                    user = "table2";
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("time");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";
                        value = dataSnapshot.getValue(String.class);
                        if (!Objects.equals(value, "null")){
                            time.setText(value);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected + " not found", Toast.LENGTH_SHORT).show();
                    }
                });
                if (selected.equals("New")){
                    time.setFocusableInTouchMode(true);
                    time.setFocusable(true);
                }
                else{
                    time.setFocusableInTouchMode(false);
                    time.setFocusable(false);
                }
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("events");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);
                        if (!Objects.equals(value, "null")){
                            events.setHint(value);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected +"not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("pressure");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            pressure.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected +" not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("flows");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            flows.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected + "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("fio2");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            fio2.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, " not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("temp");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            temp.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected +" not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("ph");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            ph.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, selected+" not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("o2");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            o2.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("pco2");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            pco2.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("be");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            be.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("hb");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            hb.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("na");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            na.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("k");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            k.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("act");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            act.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("rbs");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            rbs.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
                rootRef = FirebaseDatabase.getInstance().getReference().child(user).child(selected).child("lac");
                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = "null";  value = dataSnapshot.getValue(String.class);

                        if (!Objects.equals(value, "null"))
                            lac.setHint(value);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(table.this, "not found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                globalVariable.setStr("New");
                sp.setSelection(((ArrayAdapter)sp.getAdapter()).getPosition("New"));
            }
        });
        submit.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "table1";
            if(globalVariable12.getUser()==2)
                user12 = "table2";
            String selected = globalVariable12.getStr();
            String value = time.getText().toString();
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12).child(value);

            if(selected.equals("New")){
                rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
                rootRef12.child("title").setValue(globalVariable12.getStr1()+"Z"+value);
                for (int i=0;i<sp.getCount();i++) {
                    if (sp.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                        sp.setSelection(i);
                        break;
                    }
                }
            }
            rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12).child(value);
            rootRef12.child("time").setValue(value);

            value = "null";
            value = events.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("events").setValue(value);
            }
            else
                rootRef12.child("events").setValue("-");
            value = "null";
            value = pressure.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("pressure").setValue(value);
            }
            else
                rootRef12.child("pressure").setValue("-");
            value = "null";
            value = flows.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("flows").setValue(value);
            }
            else
                rootRef12.child("flows").setValue("-");
            value = "null";
            value = fio2.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("fio2").setValue(value);
            }
            else
                rootRef12.child("fio2").setValue("-");
            value = "null";
            value = temp.getText().toString();
            if(!(value.equals(""))) {
                rootRef12.child("temp").setValue(value);
            }
            else
                rootRef12.child("temp").setValue("-");
            value = "null";
            value = ph.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("ph").setValue(value);
            }
            else
                rootRef12.child("ph").setValue("-");
            value = "null";
            value = o2.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("o2").setValue(value);
            }
            else
                rootRef12.child("o2").setValue("-");
            value = "null";
            value = pco2.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("pco2").setValue(value);
            }
            else
                rootRef12.child("pco2").setValue("-");
            value = "null";
            value = be.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("be").setValue(value);
            }
            else
                rootRef12.child("be").setValue("-");
            value = "null";
            value = hb.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("hb").setValue(value);
            }
            else
                rootRef12.child("hb").setValue("-");
            value = "null";
            value = na.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("na").setValue(value);
            }
            else
                rootRef12.child("na").setValue("-");
            value = "null";
            value = k.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("k").setValue(value);
            }
            else
                rootRef12.child("k").setValue("-");
            value = "null";
            value = act.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("act").setValue(value);
            }
            else
                rootRef12.child("act").setValue("-");
            value = "null";
            value = rbs.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("rbs").setValue(value);
            }
            else
                rootRef12.child("rbs").setValue("-");
            value = "null";
            value = lac.getText().toString();
            if(!(value.equals(""))){
                rootRef12.child("lac").setValue(value);
            }
            else
                rootRef12.child("lac").setValue("-");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        clear.setOnClickListener(v -> {
            Global globalVariable1 = (Global) getApplicationContext();
            String user1 = "table1";
            if(globalVariable1.getUser()==2)
                user1 = "table";
            DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user1);
            rootRef1.child("title").setValue("New");

        });
        remove.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "table1";
            if(globalVariable12.getUser()==2)
                user12 = "table2";
            String selected = globalVariable12.getStr();
            DatabaseReference rootRef12;
            if(!selected.equals("New")){
                String value1 = globalVariable12.getStr1();
                String select = globalVariable12.getStr();
                String strNew = value1.replace(("Z"+select), "");
                rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12);
                rootRef12.child("title").setValue(strNew);
                rootRef12.child(select).removeValue();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
    }
    public void showTimePickerDialog(View view) {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(table.this,
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
                    time.setText(sHour1 + ":" + sMinute1+" "+tm);
                }, hour, minutes, false);
        picker.show();
    }
}
