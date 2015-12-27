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
import java.util.Collections;

/**
 * Created by shadow8t4 on 12/5/15.
 *
 */
public class FilterAdapter extends ArrayAdapter<Ingredient> {
    public View v;
    public FilterAdapter(Activity a, int id, ArrayList<Ingredient> list){
        super(a, id, list);
    }
    public static int filterselection = 99;
    public static ArrayList<Boolean> selectionselected;
    public static int temppos = 99;

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        super.getView(pos, convertView, parent);
        this.v = convertView;
        if(v==null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=vi.inflate(R.layout.list_item, null);
        }

        TextView t = (TextView) v.findViewById(R.id.text1);
        t.setText(getItem(pos).getName());

        if (pos == filterselection && temppos != pos) {
            selectionselected.set(filterselection, !selectionselected.get(filterselection));
            temppos = pos;
        }

        if(selectionselected.get(pos)) {
            t.setBackgroundColor(Color.BLUE);
        }
        else {
            t.setBackgroundColor(Color.WHITE);
        }

        return v;
    }
}
