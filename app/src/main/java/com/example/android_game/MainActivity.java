package com.example.android_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static boolean isLeftPressed;
    public static boolean isRightPressed;
    public static int playerScore = 0;

    public static TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.text_player_score);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                int score = (int) msg.obj;
                addToScore(score);
            }
        };

        GameView gameView = new GameView(this, handler);
        LinearLayout gameLayout = findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);

        LinearLayout leftButton = findViewById(R.id.leftButton);
        LinearLayout rightButton = findViewById(R.id.rightButton);
        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.leftButton:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.rightButton:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
            default:
                break;
        }
        return true;
    }

    public static void addToScore(int score){
        playerScore += score;
        tvScore.setText(String.format("%09d", playerScore));
    }

}