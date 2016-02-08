package com.example.williamtygret.project2neighborhood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        int id = getIntent().getIntExtra("id",-1);
        if(id >= 0){
           DatabaseHelper dh = DatabaseHelper.getInstance(InfoActivity.this);
            String name = dh.getPlaceName(id);
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText(name);
        }
    }
}
