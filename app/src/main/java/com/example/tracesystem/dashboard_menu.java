package com.example.tracesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class dashboard_menu extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    TextView firebasenameview;
    Button toast;

    private CardView addRecord, viewRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_menu);
        firebasenameview = findViewById(R.id.firebasename);

        // this is for username to appear after login

        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String result = finaluser.substring(0, finaluser.indexOf("@"));
        String resultemail = result.replace(".","");
        firebasenameview.setText("Welcome, "+resultemail);
//        toast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(dashboardActivity.this, users.getEmail(), Toast.LENGTH_SHORT).show();
//            }
//        });


        addRecord = (CardView)findViewById(R.id.addrecord);
        viewRecord = (CardView) findViewById(R.id.viewrecord);

        addRecord.setOnClickListener(this);
        viewRecord.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.addrecord: i = new Intent(this,addrecord.class); startActivity(i); break;
            case R.id.viewrecord: i = new Intent(this,viewrecord.class);startActivity(i); break;
            default: break;
        }
    }

    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(dashboard_menu.this,Login.class));
        Toast.makeText(dashboard_menu.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
