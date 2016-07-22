package com.ks.poc.testsendfcm;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Krit on 7/14/2016.
 */
public class FCMDownstreamMessage extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String server_key = "key=AIzaSyB1Q9TUZ8UTm3M_aQjimPRkUXf2_dHpZT4";
        String client_key;
        String content;
        String content_json_string;
        int responseCode;

        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            client_key = "f6RhbW5Sgwc:APA91bHSEyS4c38y2GaBjEkyNES6UHxG6Tnk5LMhaoY5mpKUwS9LI_u9kADuRAFj2q0TfK_AO5tvmeig7zaf050-0_B7SLgKg6mKKjq4gMf-JJxoiKpHjc2NjJW54-VGJ6pF20GnP17p";

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpURLConnection.setRequestProperty("Authorization", server_key);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();

            JSONObject notification_json_object = new JSONObject();
            try {
                notification_json_object.put("body", params[0]);
                notification_json_object.put("title", "From FCMDownstreamMessage");
                // Use icon name from drawable folder of the receiver application
                notification_json_object.put("icon", "contactimage");
                // Trigger opening of specific screen on the receiver application
                notification_json_object.put("click_action", "OPEN_DETAIL_NOTIFICATION");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject data_json_object = new JSONObject();
            try {
//                data_json_object.put("key1", "value1");
//                data_json_object.put("key2", "value2");
//                data_json_object.put("key3", "value3");
                data_json_object = new JSONObject("{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}");
                Log.d("FCMDownstreamMessage",data_json_object.toString());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject content_json_object = new JSONObject();
            try {
                content_json_object.put("to", client_key);
//                content_json_object.put("notification", notification_json_object);
                // Only send dat if need the receiver callback to triggered although the app is not running.
                content_json_object.put("data", data_json_object);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            content_json_string = content_json_object.toString();

            OutputStream output = httpURLConnection.getOutputStream();
            output.write(content_json_string.getBytes());
            output.flush();
            output.close();

            responseCode = httpURLConnection.getResponseCode();

            return Integer.toString(responseCode);
        } catch (ProtocolException e) {
            return "Error: " + e.getMessage();

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
