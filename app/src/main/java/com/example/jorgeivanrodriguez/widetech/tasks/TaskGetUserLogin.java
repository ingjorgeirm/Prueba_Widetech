package com.example.jorgeivanrodriguez.widetech.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jorgeivanrodriguez.widetech.R;
import com.example.jorgeivanrodriguez.widetech.UserProfileDetails;
import com.example.jorgeivanrodriguez.widetech.helpers.Connections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jorge Ivan Rodriguez on 15/06/2015.
 */
public class TaskGetUserLogin extends AsyncTask<String,Void,List<HashMap<String, String>>> {
    private Connections connections;
    private Context context;
    JSONObject json;
    JSONArray jArray;
    JSONObject json_data;
    WeakReference<ProgressBar> progressBarGUL;
    ProgressBar pgGUL;
    String urlEndpoint;
    Activity activity;
    Boolean conexion=true;
    public TaskGetUserLogin(Activity activity,Context context, ProgressBar pgGUL, String urlEndpoint){
        this.activity=activity;
        this.urlEndpoint=urlEndpoint;
        this.progressBarGUL=new WeakReference<ProgressBar>(pgGUL);
        this.context=context;

    }


    @Override
    protected List<HashMap<String, String>> doInBackground(String... params) {
        try {
        Connections connections= new Connections(context,urlEndpoint);

            connections.SendData(params);
            return connections.SendData(params);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            conexion=false;
        }catch (IOException e) {
            e.printStackTrace();
            conexion=true;
        } catch (JSONException e) {
            e.printStackTrace();
            conexion=true;
        }
        return null;



    }

    protected void onPostExecute(List<HashMap<String, String>> userData){
        if (conexion){
    if (userData.size()>0){
        pgGUL=progressBarGUL.get();
        pgGUL.setVisibility(View.GONE);
        alert(context,userData);
    }else{
     alertNotFound(context);

    }
        }else{
            alertConnectionProblem(context);
        }
    }

    private void alert(final Context context, final List<HashMap<String, String>> userData){

        AlertDialog.Builder  alert = new AlertDialog.Builder(
                context);

        alert.setIcon(R.drawable.send)
                .setMessage(R.string.msjGetUserLogin)
                .setCancelable(false)
                .setPositiveButton(R.string.continuar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog,
                                    int id) {

                               Intent i = new Intent(context,
                                        UserProfileDetails.class);
                                i.putExtra("FNAME", userData.get(0).get("Value").toString());
                                i.putExtra("LNAME", userData.get(1).get("Value").toString());
                                i.putExtra("PHONE", userData.get(2).get("Value").toString());
                                i.putExtra("EMAIL", userData.get(3).get("Value").toString());
                                i.putExtra("PASSWORD", userData.get(4).get("Value").toString());
                                i.putExtra("ID", userData.get(5).get("Value").toString());

                                Log.i("FNAME", userData.get(0).get("Value").toString());
                                Log.i("LNAME", userData.get(1).get("Value").toString());
                                Log.i("PHONE_USER", userData.get(2).get("Value").toString());
                                Log.i("EMAIL", userData.get(3).get("Value").toString());
                                Log.i("PASSWORD", userData.get(4).get("Value").toString());
                                Log.i("ID", userData.get(5).get("Value").toString());
                                activity.startActivity(i);
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alert.create();

        // show it
        alertDialog.show();
    }
    private void alertNotFound(final Context context){

        AlertDialog.Builder  alert = new AlertDialog.Builder(
                context);

        alert.setIcon(R.drawable.com_facebook_close)
                .setMessage(R.string.msjGetUserLoginNotFound)
                .setCancelable(false)
                .setPositiveButton(R.string.salir,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog,
                                    int id) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alert.create();

        // show it
        alertDialog.show();
    }
    private void alertConnectionProblem(final Context context){

        AlertDialog.Builder  alert = new AlertDialog.Builder(
                context);
        alert = new AlertDialog.Builder(
                context);

        alert.setIcon(R.drawable.com_facebook_close)
                .setTitle(R.string.conexion)
                .setMessage(R.string.conexionProblem)
                .setCancelable(false)
                .setPositiveButton(R.string.salir,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog,
                                    int id) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog2 = alert.create();

        // show it
        alertDialog2.show();
    }
}
