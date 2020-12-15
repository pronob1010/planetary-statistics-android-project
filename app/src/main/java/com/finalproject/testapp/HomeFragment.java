package com.finalproject.testapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    EditText nameET,distanceET,spinET;
    Button insertBTN,showdataBTN;
    Spinner spinner;
    DatabaseHelp databaseHelp;

  
    public HomeFragment() {
        
    }

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nameET = view.findViewById(R.id.nameET);
        distanceET = view.findViewById(R.id.distanceET);
        spinET = view.findViewById(R.id.spinET);

        insertBTN = view.findViewById(R.id.insertBTN);

        showdataBTN = view.findViewById(R.id.showdataBTN);
        spinner = view.findViewById(R.id.spinner);

        databaseHelp = new DatabaseHelp(getActivity());

        insertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameET.getText().toString();
                String distance = distanceET.getText().toString();
                String spin = spinET.getText().toString();
                String satellite = spinner.getSelectedItem().toString();

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if(distance.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter your distance", Toast.LENGTH_SHORT).show();
                }
                else if(spin.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter the spin", Toast.LENGTH_SHORT).show();
                }
                else if(satellite.equals("satellite")){
                    Toast.makeText(getActivity(), "Please enter satellite", Toast.LENGTH_SHORT).show();
                }
                else {

                    long id = databaseHelp.insertData(name,distance,spin,satellite);
                    nameET.setText("");
                    distanceET.setText("");
                    spinET.setText("");
                    spinner.setSelection(0);



                    Toast.makeText(getActivity(), "Data insert & ID is: "+id, Toast.LENGTH_SHORT).show();

                }
            }
        });




        showdataBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ShowDataActivity.class));
            }
        });
        
        return view;
    }
}