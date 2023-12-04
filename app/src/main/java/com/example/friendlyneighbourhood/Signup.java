package com.example.friendlyneighbourhood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText r_fname, r_lname, r_uname, r_email, r_pass;
    String fname, lname, uname, email, pass, city, locality;
    Button signup;
    Spinner regcity, reglocality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        r_fname = findViewById(R.id.et_firstName);
        r_lname = findViewById(R.id.et_lastName);
        r_uname = findViewById(R.id.et_regUsername);
        r_email = findViewById(R.id.et_regEmail);
        r_pass = findViewById(R.id.et_regPassword);

        String[] cities = {"Ajmer", "Jaipur", "Delhi", "Mumbai"};
        regcity = findViewById(R.id.spin_regCity);
        reglocality = findViewById(R.id.spin_regLocality);

        ArrayAdapter cityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regcity.setAdapter(cityAdapter);
        regcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] locAjmer = {"Ramganj", "Vaishali Nagar"};
                String[] locJaipur = {"Vaishali Nagar", "Mansarovar"};
                String[] locDelhi = {"Lajpat Nagar", "New Delhi"};
                String[] locMumbai = {"Meera Road", "Santacruz"};

                if (regcity.getSelectedItem().toString().equals("Ajmer")) {

                    ArrayAdapter locAdapter = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, locAjmer);
                    locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    reglocality.setAdapter(locAdapter);

                } else if (regcity.getSelectedItem().toString().equals("Jaipur")) {
                    ArrayAdapter locAdapter = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, locJaipur);
                    locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    reglocality.setAdapter(locAdapter);

                } else if (regcity.getSelectedItem().toString().equals("Delhi")) {
                    ArrayAdapter locAdapter = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, locDelhi);
                    locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    reglocality.setAdapter(locAdapter);

                } else if (regcity.getSelectedItem().toString().equals("Mumbai")) {
                    ArrayAdapter locAdapter = new ArrayAdapter(Signup.this, android.R.layout.simple_spinner_item, locMumbai);
                    locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    reglocality.setAdapter(locAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup = findViewById(R.id.but_register);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        ConnectionHelper conn = new ConnectionHelper();

        fname = r_fname.getText().toString();
        lname = r_lname.getText().toString();
        uname = r_uname.getText().toString();
        email = r_email.getText().toString();
        pass = r_pass.getText().toString();
        city = regcity.getSelectedItem().toString();
        locality = reglocality.getSelectedItem().toString();

        if (!fname.equals("") && !lname.equals("") && !uname.equals("") && !email.equals("") && !pass.equals("") && !city.equals("") && !locality.equals("")) {
            conn.register(fname, lname, uname, email, pass, city, locality);
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}