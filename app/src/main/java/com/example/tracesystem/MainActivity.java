package com.example.tracesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void sapCollector (View view)
    {

        startActivity(new Intent(this,Login.class));
    }
    public void sapHarvester (View view)
    {

        startActivity(new Intent(this,sapharvester_login.class));
    }

    public void admin (View view)
    {

        startActivity(new Intent(this,admin_login.class));
    }

}