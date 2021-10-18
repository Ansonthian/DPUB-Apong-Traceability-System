package com.example.tracesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, dashboard_menu.class));
        }
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