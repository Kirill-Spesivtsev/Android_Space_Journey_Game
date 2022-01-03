package com.example.android_game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoresTableAdapter extends BaseAdapter {

    ArrayList<PlayerScore> objects;

    Context context;

    LayoutInflater inflater;

    ScoresTableAdapter(Context con, ArrayList<PlayerScore> obj){
        objects = obj;
        context = con;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.list_item,null);
        TextView tvNickName = v.findViewById(R.id.text_nickname);
        TextView tvScore = v.findViewById(R.id.text_score);
        TextView tvDate = v.findViewById(R.id.text_date);
        TextView tvOrder = v.findViewById(R.id.text_order_number);

        tvNickName.setText(objects.get(i).nickname);
        tvScore.setText(Integer.toString(objects.get(i).highscore));
        tvDate.setText(objects.get(i).date);
        tvOrder.setText(Integer.toString(i+1));

        return v;
    }


    public ArrayList<PlayerScore> getList(){
        return objects;
    }
}
