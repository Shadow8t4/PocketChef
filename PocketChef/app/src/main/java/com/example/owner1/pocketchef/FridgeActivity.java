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
public class FridgeActivity extends AppCompatActivity {

    public static FridgeXMLHandler fridgexmlreader = new FridgeXMLHandler();
    public static String filename = "fridge.xml";

    public static Fridge fridge = new Fridge();
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fridge_toolbar);
        toolbar.setTitle("Pocket Chef - Fridge");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(this,R.layout.list_item, fridge.getIngredients());
        ListView list = (ListView) findViewById(R.id.fridge_list);
        list.setAdapter(adapter);

        try {
            File path = FridgeActivity.this.getFilesDir();
            File file = new File(path, filename);
            if(!file.exists()){
                file.createNewFile();
                fridgexmlreader.writeXML(fridgexmlreader.readXML(R.raw.fridge, this), filename, this);
            }
            fridge = fridgexmlreader.readXML(filename, this);
        }

        catch (IOException io_e) {
            System.out.println("IOException occurred in Main Activity.");
        }
        catch (Exception e) {
            System.out.println("An unidentified exception occurred in Main Activity.");
        }


    }   //end on create func

    @Override
    public void onResume() {
        super.onResume();
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(this,R.layout.list_item, fridge.getIngredients());
        ListView list = (ListView) findViewById(R.id.fridge_list);
        list.setAdapter(adapter);

        try {
            File path = FridgeActivity.this.getFilesDir();
            File file = new File(path, filename);
            fridge = fridgexmlreader.readXML(filename, this);
        }
        catch (Exception e) {
            System.out.println("An unidentified exception occurred in Main Activity.");
        }
    }

    public Vector<Ingredient> searchRecipe(String query) {
        Vector <Ingredient> temp = new Vector<>();
        for(int i = 0; i < fridge.getSize(); ++i) {
            if(fridge.getIngredient(i).toString().toLowerCase().contains(query.toLowerCase())) {
                temp.add(fridge.getIngredient(i));
            }
        }
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fridge_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.fridge_search);
        if(searchItem == null) {
            System.out.println("test");
        }
        android.support.v7.widget.SearchView search = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);

        context = this.getApplicationContext();

        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                ((ListView) findViewById(R.id.fridge_list)).setAdapter(new ArrayAdapter<>(context, R.layout.list_item, fridge.getIngredients()));
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
                Vector<Ingredient> temp = searchRecipe(query);
                ((ListView) findViewById(R.id.fridge_list)).setAdapter(new ArrayAdapter<>(context, R.layout.list_item, temp));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        search.setQueryHint("Search Fridge Ingredients.");
        int searchPlateId = search.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = search.findViewById(searchPlateId);
        if (searchPlate!=null) {
            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView listText = (TextView) searchPlate.findViewById(R.id.text1);
            if(listText!=null) {
                listText.setTextColor(Color.BLACK);
            }
            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
            if (searchText!=null) {
                searchText.setTextColor(Color.BLACK);
                searchText.setHintTextColor(Color.BLACK);
            }
        }
        return true;

    }

    public void Add() {
        Intent intent = new Intent(this, FridgeAddActivity.class);
        startActivity(intent);
    }

    public void Delete() {
        Intent intent = new Intent(this, FridgeDeleteActivity.class);
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
            case R.id.fridge_add:
                Add();
                break;
            case R.id.fridge_delete:
                Delete();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
