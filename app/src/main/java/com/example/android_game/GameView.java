package com.example.android_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    public static int maxX = 20;
    public static int maxY = 30;
    public static float unitW = 0;
    public static float unitH = 0;

    Handler handler;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private PlayerObject playerObject;

    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int obstacleFrequency = 50;
    private int prevObstacleTime;

    private final int speedUpFrequency = 100;
    private int prevSpeedUpTime;
    private int speedIncreaseNumber = 0;

    public GameView(Context context, Handler h) {
        super(context);
        handler = h;
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning){
            update();
            draw();
            checkCollision();
            isSpeedIncreasing();
            isObstacleAdding();
            control();
        }
    }

    private void update(){
        if(!firstTime){
            playerObject.update();
            for (Obstacle obstacle: obstacles){
                obstacle.update();
            }
        }
    }

    public void draw(){
        if (surfaceHolder.getSurface().isValid()){

            if (firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;

                playerObject = new PlayerObject(getContext());

            }
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            playerObject.draw(paint, canvas);

            for (Obstacle obstacle: obstacles){
                obstacle.draw(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try {
            gameThread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){
        for (Obstacle obstacle: obstacles){
            if (obstacle.isCollision(playerObject.x, playerObject.y, playerObject.size)){
                gameRunning = false;
            }
        }
    }

    private void isObstacleAdding(){
        if (prevObstacleTime >= obstacleFrequency){
            Obstacle obstacle = new Obstacle(getContext());
            obstacles.add(obstacle);
            prevObstacleTime = 0;
            addToScore((int) (25 * Math.log10( 10 + speedIncreaseNumber)));

        }else{
            prevObstacleTime++;
        }
    }

    private void isSpeedIncreasing(){
        if (prevSpeedUpTime >= speedUpFrequency){
            Obstacle.baseSpeedMultiplier *= 1.05;
            prevSpeedUpTime = 0;
            speedIncreaseNumber++;
        }else{
            prevSpeedUpTime++;
        }
    }

    private void addToScore(int number){
        Message mes = new Message();
        mes.obj = number;
        handler.sendMessage(mes);
    }
}
