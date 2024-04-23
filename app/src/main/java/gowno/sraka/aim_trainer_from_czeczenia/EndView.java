package gowno.sraka.aim_trainer_from_czeczenia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndView extends AppCompatActivity {
    public static boolean userWon;
    Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_view);
        TextView textView = findViewById(R.id.text);
        restartButton = findViewById(R.id.restartButton);

        if (userWon) {
            textView.setText("You won !");
        } else {
            textView.setText("You Lost !");
        }
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EndView.this, MainActivity.class));
                finish();
            }
        });
    }
}