package com.aaxs.nitrov17.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import java.util.Random;
import java.util.logging.StreamHandler;


public class MainActivity extends AppCompatActivity {

    TextView timerText,eqText,scoreText,choice1,choice2,choice3,choice4;
    Button start;
    CountDownTimer countDownTimer;
    boolean isTimerStarted=false;
    int a=0,b=0,ans=0,score=0,totalQ=0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.start), "Click here to start playing.")
                        .drawShadow(true)
                        .tintTarget(true)
                        .cancelable(false)
                        ,
                        TapTarget.forView(findViewById(R.id.scoreText), "Here's your score.")
                        .drawShadow(true)
                        .tintTarget(true)
                        .cancelable(false)
                        ,
                        TapTarget.forView(findViewById(R.id.timerText), "You have 30 seconds.")
                         .drawShadow(true)
                         .tintTarget(true)
                        .cancelable(false)
                ) .start();





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

                hideUI();
                showScore();
            }
        };

        start.setOnClickListener(new View.OnClickListener() {   //// TODO: 10/29/2016 Implement reset method 
            @Override
            public void onClick(View view) {

                isTimerStarted=true;
                countDownTimer.start();
                start.setVisibility(View.INVISIBLE);
                choice1.setVisibility(View.VISIBLE);
                choice2.setVisibility(View.VISIBLE);
                choice3.setVisibility(View.VISIBLE);
                choice4.setVisibility(View.VISIBLE);
                generateEq();
            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Log.d("onClick",getResourceName(view));
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
        //// TODO: 10/29/2016 Setup sequence buttonSetup() -> validate() -> updateScore() -> generateEq() -> buttonSetup
        int index = random.nextInt(4);
        Log.d("Random value", String.valueOf(index));
        switch (index){
            case 0:{
                choice1.setText(String.valueOf(ans));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
                break;
            }
            case 1:{
                choice2.setText(String.valueOf(ans));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
                break;
            }
            case 2:{
                choice3.setText(String.valueOf(ans));
                choice4.setText(String.valueOf(ans+random.nextInt(50)));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
                break;
            }
            case 3:{
                choice4.setText(String.valueOf(ans));
                choice1.setText(String.valueOf(ans+random.nextInt(50)));
                choice2.setText(String.valueOf(ans+random.nextInt(50)));
                choice3.setText(String.valueOf(ans+random.nextInt(50)));
                break;
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
        eqText.setText("");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisibility(View.INVISIBLE);
        choice2.setVisibility(View.INVISIBLE);
        choice3.setVisibility(View.INVISIBLE);
        choice4.setVisibility(View.INVISIBLE);
    }

    void hideUI()
    {
        timerText.setVisibility(View.INVISIBLE);
        eqText.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.INVISIBLE);
        choice1.setVisibility(View.INVISIBLE);
        choice2.setVisibility(View.INVISIBLE);
        choice3.setVisibility(View.INVISIBLE);
        choice4.setVisibility(View.INVISIBLE);
    }

    void showScore()
    {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Times's Up!")
                .setDescription("Hope you have fun.You scored "+ String.valueOf(score)+" with an accuracy of "+ calcAccu())
                .setStyle(Style.HEADER_WITH_TITLE)
                .setPositiveText("Restart")
                .setNegativeText("Dismiss")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        stateReset();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(MainActivity.this, "Play again?", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    void stateReset()
    {

        a=0;
        b=0;
        ans=0;
        score=0;
        totalQ=0;
        timerText.setVisibility(View.VISIBLE);
        eqText.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        choice1.setVisibility(View.VISIBLE);
        choice2.setVisibility(View.VISIBLE);
        choice3.setVisibility(View.VISIBLE);
        choice4.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);

        resetTimer();
        initLayout();

    }

    void resetTimer()
    {
        countDownTimer.cancel();
        isTimerStarted=false;
    }

    String calcAccu()
    {
        float val = ((float)score/(float)totalQ)*100;
        return String.valueOf(val);
    }
}


//// TODO: 10/30/2016 Add progress ring 
//// TODO: 10/30/2016 Implement dismiss option
//// TODO: 10/30/2016 Use textView instead of toast

