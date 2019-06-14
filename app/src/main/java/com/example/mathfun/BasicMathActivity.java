package com.example.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BasicMathActivity extends AppCompatActivity {

    private TextView title;
    private TextView question;
    private TextView score;
    private EditText answer;
    private Button btn;
    private int round;
    private int hits;
    private int a, b, opt, correct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        title = findViewById(R.id.tll);
        question = findViewById(R.id.sub);
        answer = findViewById(R.id.rsp);
        btn = findViewById(R.id.btn);
        score = findViewById(R.id.hits);

        questionBuilder();
    }

    public void questionBuilder() {
        title.setText("Questão " + (round + 1) + " de 5:");
        answer.setText("");
        b = Utils.random(10);
        a = Utils.randomBiggerThan(b);
        opt = Utils.random(2);
        String operator = (opt == 0) ? " + " : " - ";
        correct = (opt == 0) ? a + b : a - b;
        question.setText(a + operator + b + " = ?");
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
