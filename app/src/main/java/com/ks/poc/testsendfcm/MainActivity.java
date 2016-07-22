package com.ks.poc.testsendfcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.editTxt01);

        FCMDownstreamMessage messenger = new FCMDownstreamMessage();
        messenger.execute(message.getText().toString());
        String result;
        try {
             result = messenger.get();
        } catch (Exception e) {
            result = e.getMessage();
        }
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
}
