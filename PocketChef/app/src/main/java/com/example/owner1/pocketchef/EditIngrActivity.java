package com.example.owner1.pocketchef;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by shadow8t4 on 12/5/15.
 *
 */
public class EditIngrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingr_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_ingr_list_toolbar);
        toolbar.setTitle("Pocket Chef - Edit Recipe (List Ingredients)");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(this,R.layout.list_item, EditActivity.getTempingr());
        ListView list = (ListView) findViewById(R.id.edit_ingr_list);
        list.setAdapter(adapter);


    }   //end on create func

    @Override
    public void onResume() {
        super.onResume();

        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(this,R.layout.list_item, EditActivity.getTempingr());
        ListView list = (ListView) findViewById(R.id.edit_ingr_list);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_ingr_menu, menu);

        return true;

    }

    public void Add() {
        Intent intent = new Intent(this, EditIngrAddActivity.class);
        startActivity(intent);
    }

    public void Delete() {
        Intent intent = new Intent(this, EditIngrDeleteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.edit_ingr_add:
                Add();
                break;
            case R.id.edit_ingr_delete:
                Delete();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
