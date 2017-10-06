package ca.wednesdaypc.lnf.android;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Ticket extends AppCompatActivity {

    EditText date, ed_title, ed_Description, ed_manufacture, ed_pcolor, ed_scolor, ed_reward, ed_location;
    DatePickerDialog datePickerDialog;
    String S_title, S_Description, S_manufacture, S_pcolor, S_scolor, S_reward, S_location;
    int reward;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

//Adding data in spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
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

//How I get today date
        TextView td = (TextView) findViewById(R.id.ticketDateField);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(cal.getTime());
        td.setText(formattedDate);

//Getting datepickerDailog when click on editfield
        date = (EditText) findViewById(R.id.ItemdateEditText);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int Year = c.get(Calendar.YEAR);
                int Month = c.get(Calendar.MONTH);
                int Day = c.get(Calendar.DAY_OF_MONTH);


                // date picker dialog
                datePickerDialog = new DatePickerDialog(Ticket.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, Year, Month, Day);
                datePickerDialog.show();
            }
        });


        ed_title = (EditText) findViewById(R.id.titleEditText);
        ed_Description = (EditText) findViewById(R.id.descriptionEditText);
        ed_manufacture = (EditText) findViewById(R.id.ManufactureEditText);
        ed_pcolor = (EditText) findViewById(R.id.primarycolorEditText);
        ed_scolor = (EditText) findViewById(R.id.SecondarycolorEditText);
        ed_location = (EditText) findViewById(R.id.locationEditText);
        ed_reward = (EditText) findViewById(R.id.rewardEditText);
        submit = (Button) findViewById(R.id.button2);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckValidation();
            }
        });

    }

    public void CheckValidation() {

        intialize();

        if (!validate()) {

            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }

    }

    public boolean validate() {

        boolean valid = true;
        if (S_title.isEmpty()) {
            ed_title.setError("Please enter valid Title");
            valid = false;
        }
        if (S_Description.isEmpty()) {
            ed_Description.setError("Please enter valid Description");
            valid = false;
        }
        if (S_manufacture.isEmpty()) {
            ed_manufacture.setError("Please enter valid Manufacture");
            valid = false;
        }
        if (S_pcolor.isEmpty()) {
            ed_pcolor.setError("Please enter valid Primary Color");
            valid = false;
        }
        if (S_scolor.isEmpty()) {
            ed_scolor.setError("Please enter valid Secondary Color");
            valid = false;
        }
        if (S_reward.isEmpty()) {
            ed_reward.setError("Please enter Only Number");
            valid = false;
        }
        if (S_location.isEmpty()) {
            ed_location.setError("Please enter valid Location");
            valid = false;
        }
        return valid;
    }

    public void intialize() {

        S_title = ed_title.getText().toString().trim();
        S_Description = ed_Description.getText().toString().trim();
        S_manufacture = ed_manufacture.getText().toString().trim();
        S_pcolor = ed_pcolor.getText().toString().trim();
        S_scolor = ed_scolor.getText().toString().trim();
        S_location = ed_location.getText().toString().trim();
        S_reward = ed_reward.getText().toString().trim();

    }
}