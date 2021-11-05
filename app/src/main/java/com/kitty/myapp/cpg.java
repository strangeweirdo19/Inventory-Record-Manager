package com.kitty.myapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class cpg extends AppCompatActivity {
    Button add, remove, select;
    RadioButton a41, dn, htk, radioButton;
    RadioGroup dose;
    EditText cpg;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpg);
        dose = findViewById(R.id.dose);
        a41 = findViewById(R.id.a41);
        dn = findViewById(R.id.dn);
        htk = findViewById(R.id.htk);
        add = findViewById(R.id.add);
        cpg = findViewById(R.id.cpg);
        remove = findViewById(R.id.cleard);
        table = findViewById(R.id.table);
        select = findViewById(R.id.selectmode);
        Global globalVariable = (Global) getApplicationContext();
        String user = "data1";
        if (globalVariable.getUser() == 2)
            user = "data2";
        DatabaseReference rootRef;
        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("count");
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
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("dosetable").child(a).child("time");
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
                            Toast.makeText(cpg.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                    });
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child(user).child("dosetable").child(a).child("qty");
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
                            Toast.makeText(cpg.this, "not found", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(cpg.this, "count not found", Toast.LENGTH_SHORT).show();
            }
        });
        rootRef = FirebaseDatabase.getInstance().getReference().child(user).child("dose");
        rootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "null";
                value = dataSnapshot.getValue(String.class);
                switch (value) {
                    case "DN":
                        dose.check(R.id.dn);
                        break;
                    case "HTK":
                        dose.check(R.id.htk);
                        break;
                    case "A41":
                        dose.check(R.id.a41);
                        break;
                    case "null":
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(cpg.this, "gender not found", Toast.LENGTH_SHORT).show();
            }

        });

        add.setOnClickListener(v -> {
            Global globalVariable12 = (Global) getApplicationContext();
            String user12 = "data1";
            if (globalVariable12.getUser() == 2)
                user12 = "data2";
            DatabaseReference rootRef12 = FirebaseDatabase.getInstance().getReference().child(user12).child("count");
            rootRef12.get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    String a = String.valueOf(task.getResult().getValue());
                    Global globalVariable121 = (Global) getApplicationContext();
                    String usera = "data1";
                    if (globalVariable121.getUser() == 2)
                        usera = "data2";
                    DatabaseReference rootRef121 = FirebaseDatabase.getInstance().getReference().child(usera).child("count");
                    String value = select.getText().toString();
                    String value1 = cpg.getText().toString();
                    DatabaseReference rootRefa = FirebaseDatabase.getInstance().getReference().child(usera).child("dosetable");
                    if ((!(value.equals("Select time"))) && (!(value1.equals("")))) {
                        table.invalidate();
                        table.removeViews(0, table.getChildCount());
                        a = Integer.toString(Integer.parseInt(a) + 1);
                        rootRef121.setValue(a);
                        value = value.replace(":", "-");
                        rootRefa.child(a).child("time").setValue(value);
                        rootRefa.child(a).child("qty").setValue(value1);
                    }
                    int selectedId = dose.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    value = radioButton.getText().toString();
                    rootRefa = FirebaseDatabase.getInstance().getReference().child(usera).child("dose");
                    if (!(value.equals(""))) {
                        switch (value) {
                            case "DN":
                                rootRefa.setValue("DN");
                                break;
                            case "HTK":
                                rootRefa.setValue("HTK");
                                break;
                            case "4:1":
                                rootRefa.setValue("A41");
                                break;
                        }
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
            DatabaseReference rootRefa = FirebaseDatabase.getInstance().getReference().child(user12).child("dosetable");
            rootRefa.getRef().removeValue();
            rootRefa = FirebaseDatabase.getInstance().getReference().child(user12).child("count");
            rootRefa.setValue("0");
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

    }

    public void showTimePickerDialog(View view) {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog picker;
        picker = new TimePickerDialog(cpg.this,
                (tp, sHour, sMinute) -> {
                    String sMinute1 = String.valueOf(sMinute), sHour1 = String.valueOf(sHour);
                    if (sMinute / 10 == 0) {
                        sMinute1 = ("0" + sMinute1);
                    }
                    if (sHour / 10 == 0) {
                        sHour1 = ("0" + sHour1);
                    }
                    select.setText(sHour1 + ":" + sMinute1);
                }, hour, minutes, false);
        picker.show();
    }

}