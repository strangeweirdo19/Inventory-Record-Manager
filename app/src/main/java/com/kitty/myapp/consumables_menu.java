package com.kitty.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class consumables_menu extends AppCompatActivity {
    Button b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumable_menu);
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
        b14 = findViewById(R.id.button14);
        b15 = findViewById(R.id.button15);
        b16 = findViewById(R.id.button16);
        b17 = findViewById(R.id.button17);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        b4.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b4);
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupoxy_menu, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                if (clicked1.equals("Infant")||clicked1.equals("Neonate")||clicked1.equals("Dual Chamber CVR"))
                {
                    String clicked3 ="Oxygenator "+clicked1;
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("Oxygenator");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                }
                popup.setOnMenuItemClickListener(item1 -> {
                    String clicked2 = (String) item1.getTitle();
                    String clicked3 = "Oxygenator " +clicked1 +" "+ clicked2;
                    clicked2 = clicked1 +" "+ clicked2;
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("Oxygenator");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked2);
                    return true;
                });
                return true;
            });

            popup.show(); //showing popup menu
        });
        b5.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b5);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupecmoxy_menu, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                    String clicked3 = "ECMO Oxy " +clicked1;
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("ECMO Oxy");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });

            popup.show(); //showing popup menu

        });
        b6.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b6);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupecmoset_menu, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                popup.setOnMenuItemClickListener(item1 -> {
                    String clicked2 = (String) item1.getTitle();
                    String clicked3 = "ECMO SET " +clicked1 +" "+ clicked2;
                    clicked2 = clicked1 +" "+ clicked2;
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("ECMO SET");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked2);
                    return true;
                });
                return true;
            });
            popup.show(); //showing popup menu

        });
        b7.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b7);
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popuphemomenu, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Hemofilter " +clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Hemofilter");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });

            popup.show(); //showing popup menu

        });
        b8.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b8);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupart, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "Arterial filter " +clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("Arterial filter");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
                });


            popup.show(); //showing popup menu
        });
        b9.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b9);
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupexco, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "EXCO tubing set " +clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("EXCO tubing set");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });
            popup.show(); //showing popup menu
        });
        b10.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b10);
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.popupecmo, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "ECMO tubing set "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("ECMO tubing set");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
            });

            popup.show(); //showing popup menu
        });
        b11.setOnClickListener(v -> {
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
                    String finalUser = user;
                    String clicked3 = "CPDS-MPS N-A";
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("CPDS-MPS");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue("N-A");
        });
        b12.setOnClickListener(v -> {
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            String finalUser = user;
            String clicked3 = "CPDS-MPS(4:1) N-A";
            mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
            mDatabase.child(finalUser).child(clicked3).child("title").setValue("CPDS-MPS(4:1)");
            mDatabase.child(finalUser).child(clicked3).child("type").setValue("N-A");
        });
        b13.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b13);
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.centrihead, popup.getMenu());
            //registering popup with OnMenuItemClickListener
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                popup.setOnMenuItemClickListener(item1 -> {
                    String clicked2 = (String) item1.getTitle();
                    String clicked3 = "Centrihead " +clicked1 +" "+ clicked2;
                    clicked2 = clicked1 +" "+ clicked2;
                    mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                    mDatabase.child(finalUser).child(clicked3).child("title").setValue("Centrihead");
                    mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked2);
                    return true;
                });
                return true;
            });
            popup.show(); //showing popup menu
        });
        b14.setOnClickListener(v -> {
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            String finalUser = user;
            String clicked3 = "HTK CP N-A";
            mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
            mDatabase.child(finalUser).child(clicked3).child("title").setValue("HTK CP");
            mDatabase.child(finalUser).child(clicked3).child("type").setValue("N-A");
        });
        b15.setOnClickListener(v -> {
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            String finalUser = user;
            String clicked3 = "CELL SAVER KIT N-A";
            mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
            mDatabase.child(finalUser).child(clicked3).child("title").setValue("CELL SAVER KIT");
            mDatabase.child(finalUser).child(clicked3).child("type").setValue("N-A");
        });
        b16.setOnClickListener(v -> {
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            String finalUser = user;
            String clicked3 = "Pres Transducer N-A";
            mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
            mDatabase.child(finalUser).child(clicked3).child("title").setValue("Pres Transducer");
            mDatabase.child(finalUser).child(clicked3).child("type").setValue("N-A");
        });
        b17.setOnClickListener(v -> {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(consumables_menu.this, b17);
            //Inflating the Popup using xml file
            Global globalVariable = (Global) getApplicationContext();
            String user = "temp1";
            if(globalVariable.getUser()==2)
                user = "temp2";
            popup.getMenuInflater()
                    .inflate(R.menu.iabp, popup.getMenu());
            String finalUser = user;
            popup.setOnMenuItemClickListener(item -> {
                String clicked1 = (String) item.getTitle();
                String clicked3 = "IABP "+ clicked1;
                mDatabase.child(finalUser).child(clicked3).child("quantity").setValue("1");
                mDatabase.child(finalUser).child(clicked3).child("title").setValue("IABP");
                mDatabase.child(finalUser).child(clicked3).child("type").setValue(clicked1);
                return true;
                });
            popup.show(); //showing popup menu
        });
        }
    }
