package com.example.android_game;

import android.content.Context;

public class PlayerObject  extends GameMotionObject{
    public PlayerObject(Context context){
        bitmapId = R.drawable.spaceship_default_1;
        size = 3;
        x = 7;
        y = GameView.maxY - size - 1;
        speed = 0.5f;

        init(context);
    }

    @Override
    public void update(){
        if (MainActivity.isLeftPressed && x > 0){
            x -= speed;
        }
        if (MainActivity.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }
}
