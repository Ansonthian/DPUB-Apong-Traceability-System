package com.example.tracesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.type.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addrecord extends AppCompatActivity {
    private EditText pH,mass,comment;
    private TextView datetime,sapCollector;
    private FirebaseAuth firebaseAuth;
    public static TextView resulttextview;
    Button scanbutton, addrecordtodatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencecat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecord);
        firebaseAuth = FirebaseAuth.getInstance();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat ("EEE, d MMM yyyy HH:mm:ss");
        String dateTime;
        dateTime = currentDate.format(calendar.getTime());


        databaseReference = FirebaseDatabase.getInstance().getReference("Sap Collector");
        databaseReferencecat = FirebaseDatabase.getInstance().getReference("Sap Collector");
        resulttextview = findViewById(R.id.sapcollector);

        addrecordtodatabase = findViewById(R.id.addrecordtodatabasebutton);

        scanbutton = findViewById(R.id.buttonscan);
        datetime = findViewById(R.id.editdate);
        mass = findViewById(R.id.editvolume);
        pH= findViewById(R.id.editph);
        comment = findViewById(R.id.editcomment);
        sapCollector= findViewById(R.id.sapcollector);

        datetime.setText(dateTime);

        // String result = finaluser.substring(0, finaluser.indexOf("@"));

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scancode.class));
            }
        });

        addrecordtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addrecord();
            }
        });

    }

    // addding record to databse (addrecord group)
    public  void addrecord(){
        String datetimeValue = datetime.getText().toString();
        String massValue = mass.getText().toString();
        String pHValue = pH.getText().toString();
        String sapCollectorValue = sapCollector.getText().toString();
        String commentValue = comment.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser = users.getEmail();
        String resultemail = finaluser.replace(".","");
        if (sapCollectorValue.isEmpty()) {
            sapCollector.setError("It's Empty");
            sapCollector.requestFocus();
            return;
        }


        if(!TextUtils.isEmpty(datetimeValue)&&!TextUtils.isEmpty(massValue)&&!TextUtils.isEmpty(pHValue)&&!TextUtils.isEmpty(sapCollectorValue)){

            record record = new record(datetimeValue,massValue,pHValue,commentValue,sapCollectorValue);
            databaseReference.child("Sap Harvester").child(sapCollectorValue).child(datetimeValue).setValue(record);
            databaseReference.child("Sap Collector").child(resultemail).child(datetimeValue).setValue(record);
            databaseReference.child("Admin Review").child(datetimeValue).setValue(record);

            datetime.setText("");
            mass.setText(""+"Kg");
            pH.setText("");
            comment.setText("");
            sapCollector.setText("");
            Toast.makeText(addrecord.this,sapCollectorValue+" Added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(addrecord.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
        }
    }


    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(addrecord.this,Login.class));
        Toast.makeText(addrecord.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
