package com.kitty.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cannule_menu extends AppCompatActivity {
    Button b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cannule_menu);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b13 = findViewById(R.id.button13);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        b4.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b4);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.aortic, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Aortic-St "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Aortic-St");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
                });
            popup.show(); //showing popup menu
        });
        b5.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b5);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.aortice, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Aortic-Eopa "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Aortic-Eopa");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b6.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b6);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.svcst, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "SVC-St "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("SVC-St");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b7.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b7);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.svcma, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "SVC-Ma "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("SVC-Ma");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b8.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b8);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.ivcst, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "IVC-St "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("IVC-St");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b9.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b9);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.ivcma, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "IVC-Ma "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("IVC-Ma");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b10.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b10);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.dssvc, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "DSSVC "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("DSSVC");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b11.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b11);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.fema, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Fem A "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Fem A");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b12.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b12);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.femv, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Fem V "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Fem V");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b13.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(cannule_menu.this, b12);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.inskit, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "InsKit "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("InsKit");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        }
    }
