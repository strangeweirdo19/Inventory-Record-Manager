package com.kitty.myapp;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NotificationsFragment extends Fragment {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    EditText text;
    Button button;
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        text = view.findViewById(R.id.editTextNumberPassword);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
                if (text.getText().toString().equals("1610")) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        verifyStoragePermissions(getActivity());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
                File directory = cw.getDir("DocumentsDIR", Context.MODE_PRIVATE);
                File file = new File(directory, "sheet" + ".xlsx");
                AssetManager assetManager = getContext().getAssets();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                if (!file.exists()) {
                    Log.d("path", file.toString());
                    InputStream in;
                    OutputStream fos;
                    try {
                        in = assetManager.open("blanksheet.xlsx");
                        fos = new FileOutputStream(file);
                        copyFile(in, fos);
                        fos.flush();
                        fos.close();
                        in.close();
                        Log.d("path", "done");
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                        Toast.makeText(view.getContext(), "Failed to open file", Toast.LENGTH_SHORT).show();
                    }
                }
                InputStream in = null;
                try {
                    in = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                XSSFWorkbook workbook = null;
                try {
                    workbook = new XSSFWorkbook(in);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(view.getContext(), "Failed to excel file", Toast.LENGTH_SHORT).show();
                }
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell;
                String value;
                workbook.createCellStyle();
                XSSFCellStyle headerCellStyle;
                headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
                headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                value = dataSnapshot.child("data1/name").getValue(String.class); //This is a1
                cell = sheet.getRow(1).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/age").getValue(String.class); //This is a1
                cell = sheet.getRow(2).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Integer.parseInt(value));
                value = dataSnapshot.child("data1/gender").getValue(String.class); //This is a1
                cell = sheet.getRow(3).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value.substring(0, 1).toUpperCase() + value.substring(1));
                value = dataSnapshot.child("data1/hospnum").getValue(String.class); //This is a1
                cell = sheet.getRow(4).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/date").getValue(String.class); //This is a1
                cell = sheet.getRow(5).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/insurance").getValue(String.class); //This is a1
                cell = sheet.getRow(6).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hlmtype").getValue(String.class); //This is a1
                cell = sheet.getRow(7).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hlm").getValue(String.class); //This is a1
                cell = sheet.getRow(8).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/procedure").getValue(String.class); //This is a1
                cell = sheet.getRow(10).getCell(13);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/preopdiag").getValue(String.class); //This is a1
                cell = sheet.getRow(12).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/height").getValue(String.class); //This is a1
                cell = sheet.getRow(14).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                int bsa = Integer.parseInt(value);
                cell.setCellValue(bsa);
                value = dataSnapshot.child("data1/weight").getValue(String.class); //This is a1
                cell = sheet.getRow(15).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                bsa = bsa * Integer.parseInt(value);
                cell.setCellValue(Integer.parseInt(value));
                cell = sheet.getRow(16).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                float bsa1 = (float) sqrt(bsa / 3600);
                DecimalFormat f = new DecimalFormat("#0.00");
                cell.setCellValue(f.format(bsa1));
                value = dataSnapshot.child("data1/zerofive").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/one").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/onefive").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(4);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/oneeight").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(5);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/two").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(6);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/twotwo").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(7);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/twofour").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/surgeon").getValue(String.class); //This is a1
                cell = sheet.getRow(20).getCell(2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/perfusionist").getValue(String.class); //This is a1
                cell = sheet.getRow(20).getCell(7);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/anaesthesiologist").getValue(String.class); //This is a1
                cell = sheet.getRow(21).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hb").getValue(String.class); //This is a1
                cell = sheet.getRow(16).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/urea").getValue(String.class); //This is a1
                cell = sheet.getRow(17).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/screat").getValue(String.class); //This is a1
                cell = sheet.getRow(18).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/circhb").getValue(String.class); //This is a1
                cell = sheet.getRow(19).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(Float.parseFloat(value));
                value = dataSnapshot.child("data1/others").getValue(String.class); //This is a1
                if (!value.equals("null")) {
                    cell = sheet.getRow(19).getCell(2);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellStyle(headerCellStyle);
                    cell.setCellValue(value);
                }
                value = dataSnapshot.child("data1/srbill").getValue(String.class); //This is a1
                cell = sheet.getRow(16).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/rbs").getValue(String.class); //This is a1
                cell = sheet.getRow(17).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(f.format(Float.parseFloat(value)));
                value = dataSnapshot.child("data1/bloodgroup").getValue(String.class); //This is a1
                cell = sheet.getRow(18).getCell(15);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);

                for (DataSnapshot data : dataSnapshot.child("temp1").getChildren()) {
                    if (data.child("title").getValue(String.class).equals("Aortic-St")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(24).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Aortic-St " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Aortic-St " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Aortic-Eopa")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(25).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Aortic-Eopa " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Aortic-Eopa " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("S.V.C.-St")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(26).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("S.V.C.-St " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("S.V.C.-St " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("S.V.C.-Ma")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(27).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("S.V.C.-Ma " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("S.V.C.-Ma " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("I.V.C.St")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(28).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("I.V.C.St " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("I.V.C.St " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("I.V.C.-Ma")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(29).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("I.V.C.-Ma " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("I.V.C.-Ma " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("DSSVC")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(30).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("DSSVC " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("DSSVC " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Fem A")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(31).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Fem A " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Fem A " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Fem V")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(32).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Fem V " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Fem V " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Ins.Kit")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(33).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Ins.Kit " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Ins.Kit " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Oxygenator")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(24).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Oxygenator " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Oxygenator " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("EXCO tubing set")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(25).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("EXCO tubing set " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("EXCO tubing set " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Hemofilter")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(26).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Hemofilter " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Hemofilter " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("CPDS-MPS")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(27).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("CPDS-MPS " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("CPDS-MPS " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("CPDS-MPS(4:1)")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(28).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("CPDS-MPS(4:1) " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("CPDS-MPS(4:1) " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Arterial")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(29).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Arterial " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Arterial " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("HTK CP")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(30).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("HTK CP " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("HTK CP " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Centrihead")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(31).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Centrihead " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Centrihead " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("CELL SAVER KIT")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(32).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("CELL SAVER KIT " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("CELL SAVER KIT " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("IABP")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(33).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("IABP " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("IABP " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("ECMO Oxy")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(34).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("ECMO Oxy " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("ECMO Oxy " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("ECMO SET")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(35).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("ECMO SET " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("ECMO SET " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                    if (data.child("title").getValue(String.class).equals("Pres Transducer")) {
                        String type = data.child("type").getValue(String.class);
                        
                        int quantity = Integer.parseInt(data.child("quantity").getValue(String.class));
                        value = type + " (" + quantity + " Quantity )";
                        cell = sheet.getRow(36).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = dataSnapshot.child("stock").child("Pres Transducer " + type).child("quantity").getValue(String.class);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                        ref1.child("stock").child("Pres Transducer " + type).child("quantity").setValue(String.valueOf(Integer.parseInt(value) - quantity));
                    }
                }
                value = dataSnapshot.child("data1/crystalloid").getValue(String.class); //This is a1
                cell = sheet.getRow(24).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/colloid").getValue(String.class); //This is a1
                cell = sheet.getRow(25).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/mannitol").getValue(String.class); //This is a1
                cell = sheet.getRow(26).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hco3").getValue(String.class); //This is a1
                cell = sheet.getRow(27).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/heparin").getValue(String.class); //This is a1
                cell = sheet.getRow(28).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/totalprime").getValue(String.class); //This is a1
                cell = sheet.getRow(36).getCell(7);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/cpbon").getValue(String.class); //This is a1
                cell = sheet.getRow(25).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/cpboff").getValue(String.class); //This is a1
                cell = sheet.getRow(26).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/totalcpb").getValue(String.class); //This is a1
                cell = sheet.getRow(27).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/aoxclamp").getValue(String.class); //This is a1
                cell = sheet.getRow(29).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/aoxclampoff").getValue(String.class); //This is a1
                cell = sheet.getRow(30).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/aoctotal").getValue(String.class); //This is a1
                cell = sheet.getRow(31).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/tcaon").getValue(String.class); //This is a1
                cell = sheet.getRow(33).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/tcaoff").getValue(String.class); //This is a1
                cell = sheet.getRow(34).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/tcattl").getValue(String.class); //This is a1
                cell = sheet.getRow(35).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                int i =40;
                for (DataSnapshot data : dataSnapshot.child("table1").getChildren()) {
                    Log.i("infor", data.child("time").getValue(String.class) + "\n");
                    if (!Objects.equals(data.child("time").getValue(String.class), "null")){
                        value = data.child("time").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(0);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("events").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("pressure").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(2);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("flows").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(3);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("fio2").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(4);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("temp").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(5);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("ph").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(6);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("o2").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(7);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("pco2").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(8);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("be").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(9);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("hb").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(10);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("na").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(11);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("k").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(12);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("act").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(13);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("rbs").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(14);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        value = data.child("lac").getValue(String.class); //This is a1
                        cell = sheet.getRow(i).getCell(15);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                    i++;
                    }
                }
                value = dataSnapshot.child("data1/dose").getValue(String.class); //This is a1
                if(value.equals("A41"))
                    value = "4:1";
                cell = sheet.getRow(54).getCell(0);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(value);
                i = 2;
                for (DataSnapshot data : dataSnapshot.child("data1/dosetable").getChildren()) {
                    if (!Objects.equals(data.child("time").getValue(String.class), "null")){
                        value = data.child("time").getValue(String.class); //This is a1
                        cell = sheet.getRow(55).getCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value.replace('-',':'));
                        value = data.child("qty").getValue(String.class); //This is a1
                        cell = sheet.getRow(53).getCell(i);
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellStyle(headerCellStyle);
                        cell.setCellValue(value);
                        i += 2;
                    }
                }
                value = dataSnapshot.child("data1/totalprime").getValue(String.class); //This is a1
                cell = sheet.getRow(59).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/pumpblood").getValue(String.class); //This is a1
                cell = sheet.getRow(60).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/pumpfluids").getValue(String.class); //This is a1
                cell = sheet.getRow(61).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/diuretics").getValue(String.class); //This is a1
                cell = sheet.getRow(62).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hco3").getValue(String.class); //This is a1
                cell = sheet.getRow(63).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/cpg").getValue(String.class); //This is a1
                cell = sheet.getRow(64).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/drugs").getValue(String.class); //This is a1
                cell = sheet.getRow(65).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/totalgain").getValue(String.class); //This is a1
                cell = sheet.getRow(66).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                if (value != null)
                i = Integer.parseInt(value);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/pump").getValue(String.class); //This is a1
                cell = sheet.getRow(59).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/surgicalloss").getValue(String.class); //This is a1
                cell = sheet.getRow(60).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/urineoutput").getValue(String.class); //This is a1
                cell = sheet.getRow(61).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/cuf").getValue(String.class); //This is a1
                cell = sheet.getRow(62).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/muf").getValue(String.class); //This is a1
                cell = sheet.getRow(63).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/totalloss").getValue(String.class); //This is a1
                cell = sheet.getRow(66).getCell(8);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellStyle(headerCellStyle);
                i = abs(Integer.valueOf(value)) - abs(i);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/lossgain").getValue(String.class); //This is a1
                cell = sheet.getRow(67).getCell(3);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value + " ("+abs(i)+")");
                value = dataSnapshot.child("data1/shock").getValue(String.class); //This is a1
                cell = sheet.getRow(58).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/support").getValue(String.class); //This is a1
                cell = sheet.getRow(59).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/hematuria").getValue(String.class); //This is a1
                cell = sheet.getRow(60).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/mitral").getValue(String.class); //This is a1
                cell = sheet.getRow(61).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/aortic").getValue(String.class); //This is a1
                cell = sheet.getRow(62).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/conduit").getValue(String.class); //This is a1
                cell = sheet.getRow(63).getCell(12);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                value = dataSnapshot.child("data1/comments").getValue(String.class); //This is a1
                cell = sheet.getRow(65).getCell(10);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(value);
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(view.getContext(), "Failed to close file", Toast.LENGTH_SHORT).show();
                }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd 'at' HH_mm");
                    String docname = sdf.format(new Date());
                    File docsFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Perfusion Data Sheet/");
                    if (!docsFolder.exists()) {
                        docsFolder.mkdirs();
                    }
                    OutputStream doc = new FileOutputStream(docsFolder + "/" + docname + ".xlsx");
                    workbook.write(doc);
                    doc.close();
                    Toast.makeText(view.getContext(), "File created successfully in" + docsFolder + "/" + docname + ".xlsx", Toast.LENGTH_SHORT).show();
                    DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference().child("data1");
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
                    rootRef1.child("blood").setValue("null");
                    rootRef1.child("cpbon").setValue("null");
                    rootRef1.child("cpboff").setValue("null");
                    rootRef1.child("totalcpb").setValue("null");
                    rootRef1.child("aoxclamp").setValue("null");
                    rootRef1.child("aoxclamoff").setValue("null");
                    rootRef1.child("aoctotal").setValue("null");
                    rootRef1.child("tcaon").setValue("null");
                    rootRef1.child("tcaoff").setValue("null");
                    rootRef1.child("tcattl").setValue("null");
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child("table1");
                    rootRef1.removeValue();
                    rootRef1.child("title").setValue("New");
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child("table1").child("New");
                    rootRef1.child("act").setValue("null");
                    rootRef1.child("be").setValue("null");
                    rootRef1.child("events").setValue("null");
                    rootRef1.child("fio2").setValue("null");
                    rootRef1.child("flows").setValue("null");
                    rootRef1.child("hb").setValue("null");
                    rootRef1.child("k").setValue("null");
                    rootRef1.child("lac").setValue("null");
                    rootRef1.child("na").setValue("null");
                    rootRef1.child("o2").setValue("null");
                    rootRef1.child("pco2").setValue("null");
                    rootRef1.child("ph").setValue("null");
                    rootRef1.child("pressure").setValue("null");
                    rootRef1.child("rbs").setValue("null");
                    rootRef1.child("temp").setValue("null");
                    rootRef1.child("time").setValue("null");
                    rootRef1 = FirebaseDatabase.getInstance().getReference().child("data1");
                    rootRef1.child("crystalloid").setValue("null");
                    rootRef1.child("colloid").setValue("null");
                    rootRef1.child("mannitol").setValue("null");
                    rootRef1.child("hco3").setValue("null");
                    rootRef1.child("heparin").setValue("null");
                    rootRef1.child("totalprime").setValue("null");
                    rootRef1.child("hb").setValue("null");
                    rootRef1.child("urea").setValue("null");
                    rootRef1.child("screat").setValue("null");
                    rootRef1.child("srbill").setValue("null");
                    rootRef1.child("rbs").setValue("null");
                    rootRef1.child("bloodgroup").setValue("null");
                    rootRef1.child("circhb").setValue("null");
                    rootRef1.child("others").setValue("null");
                    rootRef1.child("name").setValue("null");
                    rootRef1.child("age").setValue("null");
                    rootRef1.child("gender").setValue("null");
                    rootRef1.child("hospnum").setValue("null");
                    rootRef1.child("date").setValue("null");
                    rootRef1.child("insurance").setValue("null");
                    rootRef1.child("hlm").setValue("null");
                    rootRef1.child("hlmtype").setValue("null");
                    rootRef1.child("shock").setValue("null");
                    rootRef1.child("support").setValue("null");
                    rootRef1.child("hematuria").setValue("null");
                    rootRef1.child("mitral").setValue("null");
                    rootRef1.child("aortic").setValue("null");
                    rootRef1.child("conduit").setValue("null");
                    rootRef1.child("comments").setValue("null");
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
                    rootRef1.child("preopdiag").setValue(value);
                    rootRef1.child("procedure").setValue(value);
                    rootRef1.child("height").setValue(value);
                    rootRef1.child("weight").setValue(value);
                    rootRef1.child("zerofive").setValue(value);
                    rootRef1.child("one").setValue(value);
                    rootRef1.child("onefive").setValue(value);
                    rootRef1.child("oneeight").setValue(value);
                    rootRef1.child("two").setValue(value);
                    rootRef1.child("twotwo").setValue(value);
                    rootRef1.child("twofour").setValue(value);
                    rootRef1.child("surgeon").setValue(value);
                    rootRef1.child("perfusionist").setValue(value);
                    rootRef1.child("anaesthesiologist").setValue(value);
                    DatabaseReference rootRefa = FirebaseDatabase.getInstance().getReference().child("data1").child("dosetable");
                    rootRefa.getRef().removeValue();
                    rootRefa = FirebaseDatabase.getInstance().getReference().child("data1").child("count");
                    rootRefa.setValue("0");
                    rootRefa = FirebaseDatabase.getInstance().getReference().child("temp1");
                    rootRefa.getRef().removeValue();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    file = new File(docsFolder + "/" + docname + ".xlsx");
                    Uri path = FileProvider.getUriForFile(cw.getApplicationContext(), cw.getApplicationContext().getPackageName() + ".provider", file);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(path, "application/vnd.ms-excel");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                } catch (IOException e) {
                    Toast.makeText(view.getContext(), "Failed to create file" + e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

                }
                else
                    Toast.makeText(view.getContext(), "Invalid Pin", Toast.LENGTH_SHORT).show();
        });

        return view;

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
