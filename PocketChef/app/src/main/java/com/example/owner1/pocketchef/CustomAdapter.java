package com.example.owner1.pocketchef;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shadow8t4 on 12/5/15.
 *
 */
public class CustomAdapter extends ArrayAdapter<Ingredient> {
    public View v;
    public CustomAdapter(Activity a, int id, ArrayList<Ingredient> list){
        super(a, id, list);
    }
    public static int selection = 99;

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        this.v = convertView;
        if(v==null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=vi.inflate(R.layout.list_item, null);
        }

        TextView t = (TextView) v.findViewById(R.id.text1);
        t.setText(getItem(pos).toString());

        if (pos == selection) {
            // set your color
            t.setBackgroundColor(Color.RED);
        }
        else {
            t.setBackgroundColor(Color.WHITE);
        }

        return v;
    }
}
