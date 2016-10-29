package com.aaxs.nitrov17.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView timerText,eqText,scoreText,choice1,choice2,choice3,choice4;
    Button start;
    CountDownTimer countDownTimer;
    Random random = new Random();
    boolean isTimerStarted=false;
    int a=0,b=0,ans=0,score=0,totalQ=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText=(TextView)findViewById(R.id.timerText);
        start=(Button)findViewById(R.id.start);
        eqText=(TextView)findViewById(R.id.eqText);
        scoreText=(TextView)findViewById(R.id.scoreText);

        choice1=(Button)findViewById(R.id.choice1);
        choice2=(Button)findViewById(R.id.choice2);
        choice3=(Button)findViewById(R.id.choice3);
        choice4=(Button)findViewById(R.id.choice4);

        choice1.setOnClickListener(onClickListener);
        choice2.setOnClickListener(onClickListener);
        choice3.setOnClickListener(onClickListener);
        choice4.setOnClickListener(onClickListener);
        
        initLayout();


        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {

                timerText.setText(String.valueOf((int)(l/1000)));

            }

            @Override
            public void onFinish() {

            }
        };

        start.setOnClickListener(new View.OnClickListener() {   //// TODO: 10/29/2016 Implement reset method 
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

            //Log.d("onClick",getResourceName(view));
            buttonSetup();
            validate(view);

        }
    };

    void generateEq()
    {

        a=random.nextInt(50);
        b=random.nextInt(50);
        ans=a+b;
        eqText.setText(String.valueOf(a)+"+"+String.valueOf(b));

        buttonSetup();

    }

    void updateScore(int res)
    {

        totalQ++;

        if(res==1)
        {
            score++;
            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
        }
        else if(res==0)
        {
            Toast.makeText(this,"Wrong!",Toast.LENGTH_SHORT).show();
        }

        scoreText.setText(String.valueOf(score)+"/"+String.valueOf(totalQ));
        generateEq();

    }


    void validate(View view)
    {
        //// TODO: 10/29/2016 To validate button ans
        //// TODO: 10/29/2016 Call the score method to update score with a parameter
        Button button = (Button)view;
        String buttonAns = button.getText().toString();
        if(buttonAns==String.valueOf(ans))
        {
            updateScore(1);
        }
        else
        {
            updateScore(0);
        }

    }

    void buttonSetup()
    {
        //// TODO: 10/29/2016 Select random correct button
        //// TODO: 10/29/2016 Add button text with random answers
        //// TODO: 10/29/2016 Setup sequence buttonSetup() -> validate() -> updateScore() -> generateEq() -> buttonSetup()
        int index = random.nextInt(3);
        switch (index){
            case 1:{
                choice1.setText(String.valueOf(ans));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
            }
            case 2:{
                choice2.setText(String.valueOf(ans));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
            }
            case 3:{
                choice3.setText(String.valueOf(ans));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
            }
            case 4:{
                choice4.setText(String.valueOf(ans));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
            }
        }
    }

    String getResourceName(View view)
    {
        return view.getResources().getResourceName(view.getId());
    }

    void initLayout()
    {
        timerText.setText("30");
        scoreText.setText("0");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    void timeUp()
    {

    }


}