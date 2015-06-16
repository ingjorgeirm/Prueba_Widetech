package com.example.jorgeivanrodriguez.widetech.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;


import com.example.jorgeivanrodriguez.widetech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Jorge Ivan Rodriguez on 15/06/2015.
 */
public class Connections {

    private List<HashMap<String, String>> userData= new ArrayList<HashMap<String, String>>();
    HashMap<String, String> map = null;
    private String urlEndpoint;
    Context context;
     	AlertDialog.Builder alertDialog;
    public Connections(Context context, String urlEndpoint){
     	   this.urlEndpoint=urlEndpoint;
        this.context=context;
    }

    public  List<HashMap<String, String>> SendData(String...params ) throws IOException, JSONException,UnknownHostException {

        StringBuilder sb = new StringBuilder();


        HttpURLConnection urlConnection = null;
        URL url = new URL(urlEndpoint);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(10000);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept-Encoding", "UTF-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.connect();
        String jsonStr = "{\"GP\":[\n" +
                "{\"Name\": \"USR\",\"Value\":" + params[0] + "},\n" +
                "{\"Name\":\"PASS\",\"Value\":" + params[1] + "},\n" +
                "{\"Name\":\"CLIENTEID\",\"Value\":" + params[2] + "},\n" +
                "{\"Name\":\"MAIL\",\"Value\":" + params[3] + "},\n" +
                "{\"Name\": \"PASSWORD\",\"Value\": " + params[4] + "},\n" +
                "{\"Name\":\"METHOD\",\"Value\":" + params[5] + "},\n" +
                "{\"Name\":\"IMEI\",\"Value\": " + params[6] + "}]}\n";


        //se crea el JSONObject aca
        JSONObject jsonObject = new JSONObject(jsonStr);


        OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
        out.write(jsonObject.toString());
        out.close();

        int HttpResult = urlConnection.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            Log.i("resultado_HTTP_OK", sb.toString());


            return parseuserData(sb.toString());

        } else {


            Log.i("resultado_no_HTTP_OK", urlConnection.getResponseMessage());

            }

        return parseuserData(sb.toString());

    }
    private List<HashMap<String, String>> parseuserData(String userDataString){

        try {

            JSONObject jb = new JSONObject(userDataString);
            JSONArray st = jb.getJSONArray("GP");

            for(int i=0;i<st.length();i++)
            {
                map = new HashMap<String, String>();
                JSONObject JsonResult = new JSONObject(st.getString(i));


                Log.i("resultadoJson",""+JsonResult.get("Name"));
                map.put("Name", (String) JsonResult.get("Name"));
                map.put("Value", (String) JsonResult.get("Value"));
                userData.add(map);

                // loop and add it to array or arraylist
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return userData;

    }
}
