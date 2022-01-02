package com.example.android_game;

import android.content.Context;
import android.util.Log;

import java.util.Random;

public class Obstacle extends GameMotionObject{


    public int radius = 1;
    public static float baseSpeedMultiplier = 1.0f;
    private float collisionError = 0.6f;
    private final float minSpeed = 0.1f;
    private final float maxSpeed = 0.12f;

    public Obstacle(Context context){
        Random rnd = new Random();

        bitmapId = R.drawable.asteroid_default_1;
        y = - GameView.maxY;
        x = rnd.nextInt(GameView.maxX) - radius;
        size = radius * 2;
        speed = (minSpeed + (maxSpeed - minSpeed) * rnd.nextFloat())
                * baseSpeedMultiplier;

        init(context);
    }

    @Override
    public void update(){
        y += speed;
    }

    public boolean isCollision(float playerX, float playerY, float playerSize){
        boolean result = !((x + size < playerX + collisionError)
                || (x > playerX + playerSize - collisionError)
                || (y + size < playerY + collisionError)
                || (y > playerY + playerSize - collisionError));
        //if (result) Log.e("@@@@@@@@@@@@@@@@@@@", playerX + "____" + playerY);
        return result;
    }
}
