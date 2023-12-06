package com.example.friendlyneighbourhood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PostJob extends AppCompatActivity {
    String puname;
    Spinner jobTypes;
    EditText jobDesc;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        Bundle extras2 = getIntent().getExtras();
        puname = extras2.getString("key2");

        Log.e("NAME", puname);

        jobTypes = findViewById(R.id.spin_selectTypeService);
        String[] serv = {"Electric", "Plumbing", "Gardening", "Packing/Moving", "Driving", "Masonry", "Woodworking"};
        ArrayAdapter adap = new ArrayAdapter(this, android.R.layout.simple_spinner_item, serv);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobTypes.setAdapter(adap);

        jobDesc = findViewById(R.id.et_jobDescription);

        post = findViewById(R.id.but_regJob);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register () {
        ConnectionHelper ch = new ConnectionHelper();
        ch.postJob(puname, jobTypes.getSelectedItem().toString(), jobDesc.getText().toString());
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}