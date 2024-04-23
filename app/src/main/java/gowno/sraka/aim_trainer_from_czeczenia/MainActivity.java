package gowno.sraka.aim_trainer_from_czeczenia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

@SuppressLint("SetTextI18n") // xd

public class MainActivity extends AppCompatActivity {

    Costamniewiem costamniewiem; // serio nie wiem jak to nazwac
    Shooter level;
    Button button;
    RelativeLayout relativeLayout;
    RelativeLayout gui;
    RelativeLayout startGui;
    CountDownTimer countDownTimer;
    long time;
    RadioGroup radioGroup;
    int hitsLeftCount;
    int hitSize;
    int maxWidth;
    int maxHeight;
    TextView timeTextView;
    TextView hitsTextView;
    TextView TextViewToHit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shooter easy = new Shooter(12000, 10, "Easy", 120);
        Shooter medium = new Shooter(10000, 10, "Medium", 100);
        Shooter hard = new Shooter(8000, 10, "Hard", 80);

        HashMap<String, Shooter> map = new HashMap<>();
        map.put("easy", easy);
        map.put("medium", medium);
        map.put("hard", hard);
        costamniewiem = new Costamniewiem(map);

        RadioButton radioButton = findViewById(R.id.rb1);
        radioButton.toggle();

        gui = findViewById(R.id.a);
        startGui = findViewById(R.id.startGui);
        startGui.setVisibility(View.VISIBLE);
        radioGroup = findViewById(R.id.radio);
        button = findViewById(R.id.startButton);
        relativeLayout = findViewById(R.id.relLayout);
        timeTextView = findViewById(R.id.timeTextView);
        hitsTextView = findViewById(R.id.hitsTextView);
        TextViewToHit = new TextView(this);
        relativeLayout.addView(TextViewToHit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                startGui.setVisibility(View.GONE);
                gui.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
                setDifficulty();
                startCountdown();
                placeCircleRandomly();
            }
        });

        TextViewToHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIfWon()) {
                    placeCircleRandomly();
                    hitsLeftCount--;
                } else endScreen(true);
                hitsTextView.setText("" + hitsLeftCount);
            }
        });
    }
    private boolean checkIfWon(){
        return hitsLeftCount==0;
    }
    private void placeCircleRandomly() {
            TextViewToHit.setBackgroundResource(R.drawable.circle);
            TextViewToHit.setVisibility(View.VISIBLE);
            TextViewToHit.setHeight(hitSize);
            TextViewToHit.setWidth(hitSize);

            maxHeight = relativeLayout.getHeight() - hitSize;
            maxWidth = relativeLayout.getWidth() - hitSize;
            Random random = new Random();

            int x = random.nextInt(maxWidth);
            int y = random.nextInt(maxHeight);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.leftMargin = x;
            layoutParams.topMargin = y;

            TextViewToHit.setLayoutParams(layoutParams);

    }
    private void endScreen(boolean didUserWin) {
        EndView.userWon = didUserWin;
        startActivity(new Intent(MainActivity.this, EndView.class));
        countDownTimer.cancel();
        finish();
    }
    private void startCountdown() {
        countDownTimer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                timeTextView.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timeTextView.setText("0");
                relativeLayout.removeView(TextViewToHit);
                endScreen(checkIfWon());
            }
        }.start();
    }
    private void setDifficulty() {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        level = costamniewiem.getLevel(radioButton.getText().toString().toLowerCase());
        time = level.getTime();
        hitsLeftCount = level.getHitsCount();
        hitSize = level.getHitSize();
        hitsTextView.setText("" + hitsLeftCount);
        timeTextView.setText(time/1000 + "");
    }

}