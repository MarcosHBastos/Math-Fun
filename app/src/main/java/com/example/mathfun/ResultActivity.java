package com.example.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent it = getIntent();
        result = findViewById(R.id.res);
        result.setText("Você acertou um total de "+it.getExtras().get("hits")+ "% das questões");
    }

    public void main(View view) {
        finish();
    }
}
