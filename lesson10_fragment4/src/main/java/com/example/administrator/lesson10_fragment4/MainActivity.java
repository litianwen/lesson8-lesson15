package com.example.administrator.lesson10_fragment4;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        //new MainActivity().finish();
    }

    String[] title = {"jasdf", "basdf", "awef", "basdf", "eqewfasd"};
    int index;

    public void replace(View v) {
        ArgsFragment fragment = ArgsFragment.newInstance(title[index]);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
        index++;
        if (index == title.length)
            index = 0;
    }
}
