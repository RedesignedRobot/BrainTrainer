package com.aaxs.nitrov17.braintrainer;

import android.content.DialogInterface;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {

    TextView timerText,eqText,scoreText,result,choice1,choice2,choice3,choice4;
    Button start;
    CountDownTimer countDownTimer;
    Random random;
    boolean isTimerStarted=false;
    int a=0,b=0,ans=0,score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText=(TextView)findViewById(R.id.timerText);
        start=(Button)findViewById(R.id.start);
        eqText=(TextView)findViewById(R.id.eqText);
        scoreText=(TextView)findViewById(R.id.scoreText);
        choice1.setOnClickListener(onClickListener);
        choice2.setOnClickListener(onClickListener);
        choice3.setOnClickListener(onClickListener);
        choice4.setOnClickListener(onClickListener);


        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {

                timerText.setText(String.valueOf((int)(l/1000)));

            }

            @Override
            public void onFinish() {

            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isTimerStarted=true;
                countDownTimer.start();
                start.setVisibility(View.INVISIBLE);
                generateEq();

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    void generateEq()
    {

        a=random.nextInt(50);
        b=random.nextInt(50);
        ans=a+b;
        eqText.setText(String.valueOf(a)+"+"+String.valueOf(b));

    }

    void updateScore(int res)
    {
        if(res==1)
        {
            score++;
            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Wrong!",Toast.LENGTH_SHORT).show();
        }

    }


    void validate()
    {
        //// TODO: 10/29/2016 To validate button ans
        //// TODO: 10/29/2016 Call the score method to update score with a parameter
    }

    void buttonSetup()
    {
        //// TODO: 10/29/2016 Select random correct button
        //// TODO: 10/29/2016 Add button text with random answers
        //// TODO: 10/29/2016 Setup sequence buttonSetup() -> validate() -> updateScore() -> generateEq() -> buttonSetup()
    }


}