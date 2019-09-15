package com.connorchurch.james.churchapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.connorchurch.james.churchapp.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;
import java.util.List;

public class ChurchFragment extends Fragment {

    private static final String FILENAME = "report.pdf";


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.church_resources, container, false);
        final PDFView pdfView = view.findViewById(R.id.pdfView);

        Spinner spinner = view.findViewById(R.id.spnPDF);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Westminster Confession of Faith");
        categories.add("Westminster Larger Catechism");
        categories.add("Westminster Shorter Catechism");
        categories.add("Catechism for Young People");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_church, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_church);


        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {

                if(position == 0) {
                    pdfView.fromAsset("westminster-confession-of-faith.pdf").load();
                }else if(position == 1) {
                        pdfView.fromAsset("westminster-larger-catechism.pdf").load();
                }else if(position == 2){
                        pdfView.fromAsset("westminster-shorter-catechism.pdf").load();
                }else if(position == 3){
                        pdfView.fromAsset("catechism-for-young-people.pdf").load();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        return view;
    }

}