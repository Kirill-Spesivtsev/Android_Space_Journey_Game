package com.example.android_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameMotionObject {
    protected float x;
    protected float y;
    protected float size;
    protected float speed;
    protected int bitmapId;
    protected Bitmap bitmap;

    void init(Context context){
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(cBitmap, (int)(size * GameView.unitW),
                (int)(size * GameView.unitH), false);
        cBitmap.recycle();
    }

    void update(){}

    void draw(Paint paint, Canvas canvas){
        canvas.drawBitmap(bitmap, x * GameView.unitW, y * GameView.unitH, paint);
    }
}
