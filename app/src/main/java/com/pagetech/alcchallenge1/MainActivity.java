package com.pagetech.alcchallenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_about;
    Button btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_about = findViewById(R.id.btn_about_alc);
        btn_profile = findViewById(R.id.btn_my_profile);

        openAbout();
        openProfile();
    }

    public void openAbout(){
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                //Toast.makeText(getApplicationContext(), "This is About button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openProfile(){
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                //Toast.makeText(getApplicationContext(), "This is Profile button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
