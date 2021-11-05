package com.kitty.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class DashboardFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button details = view.findViewById(R.id.button4);
        details.setOnClickListener(this);
        Button diag = view.findViewById(R.id.button34);
        diag.setOnClickListener(this);
        Button preop = view.findViewById(R.id.button22);
        preop.setOnClickListener(this);
        Button cannulae = view.findViewById(R.id.button24);
        cannulae.setOnClickListener(this);
        Button consumables = view.findViewById(R.id.button25);
        consumables.setOnClickListener(this);
        Button blood = view.findViewById(R.id.button26);
        blood.setOnClickListener(this);
        Button prime = view.findViewById(R.id.button27);
        prime.setOnClickListener(this);
        Button table = view.findViewById(R.id.button29);
        table.setOnClickListener(this);
        Button cpg = view.findViewById(R.id.button30);
        cpg.setOnClickListener(this);
        Button fluid = view.findViewById(R.id.button31);
        fluid.setOnClickListener(this);
        Button others = view.findViewById(R.id.button33);
        others.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.button4:
                intent = new Intent(v.getContext(), patients.class);
                startActivity(intent);
                break;
            case R.id.button34:
                intent = new Intent(v.getContext(), diagnosis.class);
                startActivity(intent);
                break;
            case R.id.button22:
                intent = new Intent(v.getContext(), preoplab.class);
                startActivity(intent);
                break;
            case R.id.button24:
                intent = new Intent(v.getContext(), cannule_menu.class);
                startActivity(intent);
                break;
            case R.id.button25:
                intent = new Intent(v.getContext(), consumables_menu.class);
                startActivity(intent);
                break;
            case R.id.button26:
                intent = new Intent(v.getContext(), timer.class);
                startActivity(intent);
                break;
            case R.id.button27:
                intent = new Intent(v.getContext(), prime.class);
                startActivity(intent);
                break;
            case R.id.button29:
                intent = new Intent(v.getContext(), table.class);
                startActivity(intent);
                break;
            case R.id.button30:
                intent = new Intent(v.getContext(), cpg.class);
                startActivity(intent);
                break;
            case R.id.button31:
                intent = new Intent(v.getContext(), fluidmenu.class);
                startActivity(intent);
                break;
            case R.id.button33:
                intent = new Intent(v.getContext(), others.class);
                startActivity(intent);
                break;
        }
    }
}