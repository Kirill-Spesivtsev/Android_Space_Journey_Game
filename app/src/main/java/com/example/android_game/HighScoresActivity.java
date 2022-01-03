package com.example.android_game;

import static java.util.Collections.reverseOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScoresActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private ArrayList<PlayerScore> scoresList = new ArrayList<>();
    private ScoresTableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        pullData();

        adapter = new ScoresTableAdapter(this, scoresList);
        ListView lv = findViewById(R.id.list_highscore);
        lv.setAdapter(adapter);
    }

    @SuppressLint("Range")
    public void pullData(){
        String query = "SELECT " + "* " + "FROM " + DatabaseHelper.TABLE + " ;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 0){
            Toast.makeText(this,"Пусто...", Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayList<PlayerScore> sList = new ArrayList<>();
            while (cursor.moveToNext()) {
                PlayerScore newScore = new PlayerScore();
                newScore.nickname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NICKNAME));
                newScore.highscore = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_HIGHSCORE));
                newScore.date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
                sList.add(newScore);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sList.sort(Comparator.comparing(o -> o.highscore * -1));
            }
            scoresList.clear();
            scoresList.addAll(sList);
        }
        cursor.close();
    }
}