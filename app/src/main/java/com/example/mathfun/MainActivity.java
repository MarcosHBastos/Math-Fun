package com.example.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void count(View view) {
        Intent it = new Intent(MainActivity.this, CountActivity.class);
        startActivity(it);
    }

    public void basicMath(View view) {
        Intent it = new Intent(MainActivity.this, BasicMathActivity.class);
        startActivity(it);
    }

    public void biggest(View view) {
        Intent it = new Intent(MainActivity.this, BiggestActivity.class);
        startActivity(it);
    }
}
