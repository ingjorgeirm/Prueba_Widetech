package com.example.jorgeivanrodriguez.widetech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Created by Jorge Ivan Rodriguez on 16/06/2015.
 */
public class UserProfileDetails extends Activity {
    private ViewHolder holder;
    protected void onCreate(Bundle savedInstanceState) {
        // Se especifica que va a ser full screen la activity

        super.onCreate(savedInstanceState);
        // se especifica que va a tener el layout del activity main el cual
        // tiene la imagen del logo para parchate
        setContentView(R.layout.activity_userprofiledetails);
        holder=new ViewHolder();
        Intent i=getIntent();
        holder.etFname=(EditText)findViewById(R.id.etFname);
        holder.etLname=(EditText)findViewById(R.id.etLname);
        holder.etPhone=(EditText)findViewById(R.id.etPhone);
        holder.etEmail=(EditText)findViewById(R.id.etEmail);
        holder.etPassword=(EditText)findViewById(R.id.etPassword);
        holder.etId=(EditText)findViewById(R.id.etId);

        holder.etFname.setText(i.getStringExtra("FNAME"));
        holder.etLname.setText(i.getStringExtra("LNAME"));
        holder.etPhone.setText(i.getStringExtra("PHONE"));
        holder.etEmail.setText(i.getStringExtra("EMAIL"));
        holder.etPassword.setText(i.getStringExtra("PASSWORD"));
        holder.etId.setText(i.getStringExtra("ID"));



    }
    private class ViewHolder{
        EditText etFname;
        EditText etLname;
        EditText etPhone;
        EditText etEmail;
        EditText etPassword;
        EditText etId;



    }
}
