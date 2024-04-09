package gowno.sraka.aim_trainer_from_czeczenia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {

    Button button;
    RelativeLayout relativeLayout;
    int time;
    int hitsLeft;
    int maxWidth;
    int maxHeight;
    TextView timeTextView;
    TextView hitsTextView;
    TextView TextViewToHit;
    @SuppressLint("MissingInflatedId")//do wywalenia xd xd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = 10;
        hitsLeft = 5;

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
                countdown();
                generate();
            }
        });

        TextViewToHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hitsLeft > 0) {
                    generate();
                    hitsLeft--;
                    hitsTextView.setText("Left hit:" + hitsLeft);
                } else {
                    hitsTextView.setText("Left hit:" + hitsLeft);
                    Toast.makeText(MainActivity.this,"Congrats!", Toast.LENGTH_SHORT).show();
                    restart();
                }
            }
        });
    }
    private void generate() {
        if (hitsLeft != 0) {
            TextViewToHit.setText("     ");
            TextViewToHit.setTextSize(40);
            TextViewToHit.setBackgroundResource(R.drawable.circle);
            TextViewToHit.setVisibility(View.VISIBLE);

            // wartosc "70" w ponizszych 2 linijkach zeby nie wychodzilo poza ekran, zmienic jesli dalej to robi

            maxHeight = relativeLayout.getHeight() - TextViewToHit.getHeight() - 70;
            maxWidth = relativeLayout.getWidth() - TextViewToHit.getWidth() - 70;

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

            hitsTextView.setText("Left hit:" + hitsLeft);
        }
    }
    private void restart() {
        relativeLayout.removeView(TextViewToHit);
        time = 10;
        hitsLeft = 5;
        hitsTextView.setText("Left hit:" + hitsLeft);
        timeTextView.setText("Time: "+ time);
        button.setVisibility(View.VISIBLE);
    }
    private void countdown() {
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeTextView.setText("Time: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timeTextView.setText("Time: 0");
                relativeLayout.removeView(TextViewToHit);
                if (hitsLeft != 0) {
                    Toast.makeText(MainActivity.this, "You failed, try again", Toast.LENGTH_LONG).show();
                    restart();
                }
            }
        }.start();
    }
}