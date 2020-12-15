package com.finalproject.testapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment {
    
    EditText nameET,distanceET,searchET,spinET,satelliteET;
    DatabaseHelp databaseHelp;
    String ID;
    Button searchBtn,updateBtn;

    

    public UpdateFragment() {
        // Required empty public constructor
    }

  

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        
        nameET = view.findViewById(R.id.nameET);
        distanceET = view.findViewById(R.id.distanceET);
        spinET = view.findViewById(R.id.spinET);
        satelliteET = view.findViewById(R.id.satelliteET);
        searchET = view.findViewById(R.id.searchET);
        databaseHelp = new DatabaseHelp(getActivity());
        searchBtn = view.findViewById(R.id.searchBtn);

        updateBtn = view.findViewById(R.id.updateBtn);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = searchET.getText().toString();

                if(ID.isEmpty()){
                    Toast.makeText(getActivity(), "Enter ID for Search", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor cursor = databaseHelp.searchData(Integer.parseInt(ID));

                    while (cursor.moveToNext()){

                        String name = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_NAME));
                        String distance = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_DISTANCE));
                        String spin = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SPIN));
                        String bg = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SATELLITE));

                        nameET.setText(name);
                        distanceET.setText(distance);
                        spinET.setText(spin);
                        satelliteET.setText(bg);
                    }

                    searchET.setText("");
                }
            }
        });



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String distance = distanceET.getText().toString();
                String spin = spinET.getText().toString();
                String satellite = satelliteET.getText().toString();

                if(name.isEmpty() || distance.isEmpty() || spin.isEmpty() || satellite.isEmpty() ){
                    Toast.makeText(getActivity(), "Please Search First", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check = databaseHelp.updateData(Integer.parseInt(ID),name,distance,spin,satellite);

                    if(check){
                        Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                        nameET.setText("");
                        distanceET.setText("");
                        spinET.setText("");
                        satelliteET.setText("");
                    }
                    else {
                        Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        
        return view;
    }
}