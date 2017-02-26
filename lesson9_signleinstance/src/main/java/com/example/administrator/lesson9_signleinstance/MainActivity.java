package com.example.administrator.lesson9_signleinstance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
    }

    public void finish(View v) {
        Intent intent = new Intent();
        intent.putExtra("msg", et.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}
