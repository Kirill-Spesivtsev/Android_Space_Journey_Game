package com.example.android_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static boolean isLeftPressed;
    public static boolean isRightPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = new GameView(this);
        LinearLayout gameLayout = findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);

        gameView.setOnTouchListener(this);


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        float width = (float) metrics.widthPixels;
        float x = motionEvent.getX();


        if (x >= width / 2) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    isLeftPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isLeftPressed = false;
                    break;
            }
        }
        else if (x < width / 2) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    isRightPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isRightPressed = false;
                    break;
            }


        }



        return true;
    }
}