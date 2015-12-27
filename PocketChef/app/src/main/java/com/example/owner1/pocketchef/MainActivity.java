package com.example.owner1.pocketchef;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    public static RecipeManager recipebook = new RecipeManager();
    public static RecipeBookXMLHandler xmlreader = new RecipeBookXMLHandler();
    public static String fileName = "recipebook.xml";

    public static Context context;
    public static Recipe details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pocket Chef");
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item, recipebook.getRecipeNames());
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        try {
            File path = MainActivity.this.getFilesDir();
            File file = new File(path, fileName);
            if(!file.exists()){
                file.createNewFile();
                System.out.println("Writing default Recipebook.xml");
                xmlreader.writeXML(xmlreader.readXML(R.raw.recipebook, this), fileName, this);
                //file.createNewFile();
            }
            recipebook = xmlreader.readXML(fileName, this);
        }

        catch (IOException io_e) {
            System.out.println("IOException occurred in Main Activity.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.toString());
            System.out.println("An unidentified exception occurred in Main Activity.");
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                if(FilterMenu.getFilteringredients() != null && !FilterMenu.getFilteringredients().isEmpty()) {
                    ArrayList<Recipe> temp = recipebook.filter(FilterMenu.getFilteringredients(), FilterMenu.getMinc(), FilterMenu.getMaxc(), FilterMenu.getMint(), FilterMenu.getMaxt());
                    details = temp.get(position);
                }
                else {
                    details = recipebook.getRecipe(position);
                }
                RecipeDetails(view);
            }
        });


    }   //end on create func

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Recipe> temp;
        ArrayList<String> temps = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, recipebook.getRecipeNames());
        if((FilterMenu.getFilteringredients() != null && !FilterMenu.getFilteringredients().isEmpty()) || (FilterMenu.getMaxc() != Double.MAX_VALUE)) {

            String formatfilter = "Filter\n";

            if(FilterMenu.getMaxc() == Double.MAX_VALUE) {
                formatfilter += "$" + FilterMenu.getMinc() + "- $infinite\n";
            }
            else {
                formatfilter += "$" + FilterMenu.getMinc() + "- $" + FilterMenu.getMaxc() + "\n";
            }
            if(FilterMenu.getMaxt() == Double.MAX_VALUE) {
                formatfilter += FilterMenu.getMint() + "minutes - infinite minutes\n";
            }
            else {
                formatfilter += FilterMenu.getMint() + "minutes - " + FilterMenu.getMaxt() + "minutes\n";
            }

            for (int i = 0; i < FilterMenu.getFilteringredients().size(); i++) {
                formatfilter += FilterMenu.getFilteringredients().get(i).getName();
            }

            Toast.makeText(this, formatfilter, Toast.LENGTH_SHORT).show();
            temp = recipebook.filter(FilterMenu.getFilteringredients(), FilterMenu.getMinc(), FilterMenu.getMaxc(), FilterMenu.getMint(), FilterMenu.getMaxt());
            for(int i = 0; i < temp.size(); i++) {
                temps.add(temp.get(i).getName());
            }
            adapter = new ArrayAdapter<>(this,R.layout.list_item, temps);
        }
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        try {
            recipebook = xmlreader.readXML(fileName, this);
        }

        catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public ArrayList<Recipe> searchRecipe(String query) {
        ArrayList <Recipe> temp = new ArrayList<>();
        for(int i = 0; i < recipebook.getRecipes().size(); ++i) {
            if(recipebook.getRecipes().get(i).toString().toLowerCase().contains(query.toLowerCase())) {
               temp.add(recipebook.getRecipes().get(i));
            }
        }
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
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

                ((ListView) findViewById(R.id.list)).setAdapter(new ArrayAdapter<>(context, R.layout.list_item, recipebook.getRecipeNames()));
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
                RecipeManager temp = new RecipeManager(searchRecipe(query));
                ((ListView) findViewById(R.id.list)).setAdapter(new ArrayAdapter<>(context, R.layout.list_item, temp.getRecipeNames()));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        search.setQueryHint("Search Recipes.");
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

    public void RecipeDetails(View view) {
        Intent intent = new Intent(this, RecipeDetails.class);
        startActivity(intent);
    }

    public void FilterMenu(View view) {
        Intent intent = new Intent(this, FilterMenu.class);
        //EditText editText = (EditText) findViewById(R.id.Filter);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void AddRecipeActivity(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void FridgeActivity(View view) {
        Intent intent = new Intent(this, FridgeActivity.class);
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
            case R.id.button:
                AddRecipeActivity(findViewById(R.id.button));
                break;
            case R.id.button2:
                FilterMenu(findViewById(R.id.button));
                FilterAdapter.selectionselected = new ArrayList<>(Collections.nCopies(recipebook.getAllUniqueIngredients().size(), false));
                break;
            case R.id.button3:
                FridgeActivity(findViewById(R.id.button));
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}



