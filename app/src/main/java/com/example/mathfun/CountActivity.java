package com.example.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {

    private TextView title;
    private TextView subtitle;
    private TextView score;
    private ImageView img;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Integer[] imgs = {R.drawable.um, R.drawable.dois, R.drawable.tres, R.drawable.quatro, R.drawable.cinco,
            R.drawable.seis, R.drawable.sete, R.drawable.oito, R.drawable.nove, R.drawable.dez};
    private String[] names = {"Pikachus", "Squirtles", "Charmanders", "Bulbasaurs", "Psyducks",
            "Meowths", "Snorlax", "Eevees", "Dratinis", "Jigglypuffs"};
    private int[] indexes;
    private int round;
    private int hits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        title = findViewById(R.id.tll);
        subtitle = findViewById(R.id.sub);
        score = findViewById(R.id.hits);
        img = findViewById(R.id.imgView);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        round = hits = 0;
        indexes = Utils.getRandomNumbers(5);

        questionBuilder();
    }

    public void questionBuilder() {
        title.setText("Questão " + (round + 1) + " de 5:");
        subtitle.setText("Quantos " + names[indexes[round]] + " há na imagem?");
        img.setImageResource(imgs[indexes[round]]);
        int[] answers = Utils.getRandomWithMandatory((indexes[round] + 1));
        btn1.setText(String.valueOf(answers[0]));
        btn2.setText(String.valueOf(answers[1]));
        btn3.setText(String.valueOf(answers[2]));
        score.setText("Total de acertos: " + hits);
    }

    public void answer1(View view) {
        checkAnswer(Integer.parseInt(btn1.getText().toString()));
    }

    public void answer2(View view) {
        checkAnswer(Integer.parseInt(btn2.getText().toString()));
    }

    public void answer3(View view) {
        checkAnswer(Integer.parseInt(btn3.getText().toString()));
    }

    public void checkAnswer(int shot) {
        int correct = (indexes[round] + 1);
        String call = "";
        String shoutout = "";
        if (shot == correct) {
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
