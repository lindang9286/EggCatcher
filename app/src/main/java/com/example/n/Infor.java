package com.example.n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Infor extends AppCompatActivity {

    ImageButton btnHome;
    TextView tvReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        btnHome = (ImageButton) findViewById(R.id.buttonHome);
        tvReport = (TextView) findViewById(R.id.textViewReport);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Infor.this,home.class);
                startActivity(intent);
            }
        });

        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/anhnguyen.0611"));
                startActivity(intent);
            }
        });
    }
}