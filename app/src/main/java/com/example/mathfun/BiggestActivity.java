package com.example.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiggestActivity extends AppCompatActivity {

    private TextView title;
    private TextView subtitle;
    private TextView digits;
    private TextView score;
    private EditText answer;
    private Button btn;
    private List<Integer> numbers;
    private int correct;
    private int round;
    private int hits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biggest);

        title = findViewById(R.id.tll);
        subtitle = findViewById(R.id.sub);
        digits = findViewById(R.id.digits);
        answer = findViewById(R.id.rsp);
        btn = findViewById(R.id.btn);
        score = findViewById(R.id.hits);

        questionBuilder();
    }

    public void questionBuilder() {
        title.setText("Questão " + (round + 1) + " de 5:");
        answer.setText("");
        numbers = new ArrayList<>();
        numbers.add(Utils.random(10));
        numbers.add(Utils.random(10));
        numbers.add(Utils.random(10));
        digits.setText(numbers.get(0) + " | " + numbers.get(1) + " | " + numbers.get(2));
        Collections.sort(numbers);
        correct = (numbers.get(2) * 100) + (numbers.get(1) * 10) + numbers.get(0);
        score.setText("Total de acertos: " + hits);
    }

    public void check(View view) {
        String a = answer.getText().toString();
        if ("".equals(a)) {
            Toast.makeText(this, "Informe sua resposta", Toast.LENGTH_SHORT).show();
        } else {
            int parsedAnswer = Integer.parseInt(a);
            String call = "";
            String shoutout = "";
            if (parsedAnswer == correct) {
                call = "Acertou!";
                shoutout = "Sua resposta está correta, parabéns!";
                Utils.popAlertDialog(call, shoutout, this);
                hits++;
            } else {
                call = "Errou :(";
                shoutout = "A resposta correta seria: " + correct;
                Utils.popAlertDialog(call, shoutout, this);
            }
            round++;
            if (round != 5) {
                questionBuilder();
            } else {
                Intent it = new Intent(this, ResultActivity.class);
                it.putExtra("hits", (hits * 20));
                it.putExtra("call", call);
                it.putExtra("shoutout", shoutout);
                startActivity(it);
                finish();
            }
        }
    }
}
