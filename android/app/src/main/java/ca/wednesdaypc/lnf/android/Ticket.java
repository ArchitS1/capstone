package ca.wednesdaypc.lnf.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;



public class Ticket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list= new ArrayList<String>();
        list.add("Animals / Pets");
        list.add("Bags, Baggage, Luggage");
        list.add("Clothing");
        list.add("Collectors Items");
        list.add("Currency / Money");
        list.add("Documents / Literature");
        list.add("Electronics");
        list.add("Household / Tools");
        list.add("Jewelry");
        list.add("Mail / Parcel");
        list.add("Media");
        list.add("Medical");
        list.add("Musical Equipment");
        list.add("Personal Accessories");
        list.add("Sporting Goods");
        list.add("Toys");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }
}
