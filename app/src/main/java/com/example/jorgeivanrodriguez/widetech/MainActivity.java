package com.example.jorgeivanrodriguez.widetech;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jorgeivanrodriguez.widetech.tasks.TaskGetUserLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    private ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holder=new ViewHolder();
        holder.etUsr=(EditText)findViewById(R.id.etUsr);
        holder.etPass=(EditText)findViewById(R.id.etPass);
        holder.etEmail=(EditText)findViewById(R.id.etEmail);
        holder.etClienteId=(EditText)findViewById(R.id.etClienteId);
        holder.etPassword=(EditText)findViewById(R.id.etPassword);
        holder.pgGUL=(ProgressBar)findViewById(R.id.pgGUL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.publicar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send) {
            String urlEndpoint = "http://taxisws.widetech.co/API/rest/CabAdvancedRequestRest";
           /* String USR="taxi";
            String PASS="taxi";
            String CLIENTEID= "33047";
            String MAIL="dmendez@widetech.com.co";
            String PASSWORD= "12345";*/
            String METHOD="GETUSERLOGIN";
            String IMEI="353922053883383";
/*
            TaskGetUserLogin taskGetUserLogin = new TaskGetUserLogin(MainActivity.this,this,holder.pgGUL,urlEndpoint);
            taskGetUserLogin.execute(USR,PASS,CLIENTEID
                    ,MAIL,PASSWORD,METHOD,IMEI);*/

            if(!usrIsValid(holder.etUsr.getText().toString())){
                Toast.makeText(this, R.string.valUsr, Toast.LENGTH_SHORT).show();
                holder.etUsr.setBackgroundColor(getResources().getColor(R.color.valField));
                Log.i("usrIsValid", getString(R.string.valUsr));

            }else if(passIsValid(holder.etPass.getText().toString())){
                Toast.makeText(this, getString(R.string.valPass), Toast.LENGTH_SHORT).show();
                holder.etPass.setBackgroundColor(getResources().getColor(R.color.valField));

            }else if(!clienteIdIsValid(holder.etClienteId.getText().toString())){
                Toast.makeText(this, getString(R.string.valClientId), Toast.LENGTH_SHORT).show();
                holder.etClienteId.setBackgroundColor(getResources().getColor(R.color.valField));

            }else if(!emailIsValid(holder.etEmail.getText().toString())){
                Toast.makeText(this, getString(R.string.valEmail), Toast.LENGTH_SHORT).show();
                holder.etEmail.setBackgroundColor(getResources().getColor(R.color.valField));

            }else if(passIsValid(holder.etPassword.getText().toString())){
                Toast.makeText(this, getString(R.string.valPassword), Toast.LENGTH_SHORT).show();
                holder.etPassword.setBackgroundColor(getResources().getColor(R.color.valField));

            }else {
                holder.etUsr.setVisibility(View.GONE);
                holder.etPass.setVisibility(View.GONE);
                holder.etClienteId.setVisibility(View.GONE);
                holder.etEmail.setVisibility(View.GONE);
                holder.etPassword.setVisibility(View.GONE);
                holder.pgGUL.setVisibility(View.VISIBLE);


          TaskGetUserLogin taskGetUserLogin = new TaskGetUserLogin(MainActivity.this,this,holder.pgGUL,urlEndpoint);
                taskGetUserLogin.execute(holder.etUsr.getText().toString(),holder.etPass.getText().toString(),holder.etClienteId.getText().toString()
                        ,holder.etEmail.getText().toString(),holder.etPassword.getText().toString(),METHOD,IMEI);
            }
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean emailIsValid(String text){
        Pattern patternEmail = Pattern
                .compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcherEmail = patternEmail.matcher(text);
        boolean bsEmail = matcherEmail.matches();
        Log.i("bsEmail",String.valueOf(bsEmail));
        return bsEmail;
    }

    private boolean usrIsValid(String text){
        Pattern patternUsr = Pattern
                .compile("^[a-z0-9_-]{3,15}$");
        Matcher matcherUsr = patternUsr.matcher(text);
        boolean bsUsr = matcherUsr.matches();
        Log.i("bsUsr",String.valueOf(bsUsr));
        return bsUsr;
    }
    private boolean clienteIdIsValid(String text){
        Pattern patternNum = Pattern
                .compile("^(0|[1-9][0-9]*)$");
        Matcher matcherClienteId = patternNum.matcher(text);
        boolean bsClienteId = matcherClienteId.matches();
        Log.i("bsClienteId",String.valueOf(bsClienteId));
        return bsClienteId;
    }
    private boolean passIsValid(String text){
        Pattern patternPass = Pattern
                .compile("^{3,15}$");
        Matcher matcherPass = patternPass.matcher(text);
        boolean bsPass = matcherPass.matches();
        Log.i("bsPass",String.valueOf(bsPass));
        return bsPass;
    }
    private class ViewHolder{
        EditText etUsr;
        EditText etPass;
        EditText etEmail;
        EditText etClienteId;
        EditText etPassword;
        ProgressBar pgGUL;



    }
}
