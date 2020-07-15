package com.example.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumText;
    Button button0 ;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button goButton ;
    int positionOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<>();
    ConstraintLayout gameConstraint;
    CountDownTimer countDownTimer;
    TextView timerText;
    Button playAgain;
    int score = 0;
    int numberOfQuestion = 0;
    TextView scoreText;
    TextView resultText;




    public void goButton(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameConstraint.setVisibility(View.VISIBLE);
        timerText.setText("30s");

        playAgain(findViewById(R.id.timerTextView));


    }


    public void chooseAnswer(View view) {

        if (positionOfCorrectAnswer == Integer.parseInt(view.getTag().toString())) {

            score++;
            resultText.setText("Correct ;)");

        } else {

            resultText.setText("wrong :(");

        }

        numberOfQuestion++;
        scoreText.setText(numberOfQuestion + "/" + score);
        generateGame();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumText = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        goButton = findViewById(R.id.goButton);
        gameConstraint = findViewById(R.id.gameConstraint);
        timerText = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgainButton);
        scoreText = findViewById(R.id.scoreTextView);
        resultText = findViewById(R.id.resultTextView);


        goButton.setVisibility(View.VISIBLE);
        gameConstraint.setVisibility(View.INVISIBLE);

        generateGame();


    }

    public void generateGame() {

        Random rand = new Random();

        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        sumText.setText(a + "+" + b);


        positionOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4 ; i++) {
            if (positionOfCorrectAnswer == i) {
                answers.add(a + b);

            } else {
                int wrongAnswer = rand.nextInt(101);
                while (a + b == wrongAnswer) {
                    wrongAnswer = rand.nextInt(101);
                }

                answers.add(wrongAnswer);

            }

        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }

    public void playAgain(View view) {

        timerText.setText("30s");
        score = numberOfQuestion = 0;
        scoreText.setText("0/0");
        generateGame();
        playAgain.setVisibility(View.INVISIBLE);
        resultText.setText("");

        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);
                resultText.setText(scoreText.getText());

            }
        }.start();

    }

}