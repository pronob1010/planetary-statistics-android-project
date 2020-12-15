package com.finalproject.testapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteFragment extends Fragment {

    Button del_BTN,viewBTN;
    EditText searchET;
    DatabaseHelp databaseHelp;

    public DeleteFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);


        del_BTN = view.findViewById(R.id.del_BTN);
        viewBTN = view.findViewById(R.id.viewBtn);
        searchET = view.findViewById(R.id.searchET);
        databaseHelp = new DatabaseHelp(getActivity());




        viewBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ID = searchET.getText().toString();

                if(ID.isEmpty()){
                    Toast.makeText(getActivity(), "Enter ID for Search", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor cursor = databaseHelp.searchData(Integer.parseInt(ID));

                    while (cursor.moveToNext()){

                        String name = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_NAME));
                        String distance = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_DISTANCE));
                        String spin = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SPIN));
                        String satellite = cursor.getString(cursor.getColumnIndex(databaseHelp.COL_SATELLITE));

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Search Result for ID: "+ID);
                        builder.setMessage("Planet Name: "+name+"\nDistance: "+distance+"\nSpin: "+spin+"\nSatellite: "+satellite);
                        builder.setCancelable(false);
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }


                }

            }
        });


        del_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchID = searchET.getText().toString();


                if(searchID.isEmpty()){
                    Toast.makeText(getActivity(), "Please Correct ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    int check = databaseHelp.deleteData(Integer.parseInt(searchID));

                    if(check>0){
                        Toast.makeText(getActivity(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        searchET.setText("");
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