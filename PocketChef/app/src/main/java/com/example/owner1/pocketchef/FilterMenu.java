package com.example.owner1.pocketchef;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FilterMenu extends AppCompatActivity {

    private static ListView filter;
    private static Context context;
    private static ArrayList<Ingredient> filteringredients = new ArrayList<>();
    private static double minc = 0;
    private static double maxc = Double.MAX_VALUE;
    private static double mint = 0;
    private static double maxt = Double.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        toolbar.setTitle("Pocket Chef - Filter");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final FilterAdapter filteradapter = new FilterAdapter(this, R.layout.list_item, MainActivity.recipebook.getAllUniqueIngredients());
        filter = (ListView) findViewById(R.id.filter_list);
        filter.setLongClickable(false);
        filter.setAdapter(filteradapter);

        context = this.getApplicationContext();

        filter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                FilterAdapter.filterselection = position;
                filteradapter.notifyDataSetChanged();
                //selection = 99;
            }
        });

        filteringredients = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.filter:
                EditText mincost = (EditText) findViewById(R.id.edit_min_cost);
                EditText maxcost = (EditText) findViewById(R.id.edit_max_cost);
                EditText mintime = (EditText) findViewById(R.id.edit_min_time);
                EditText maxtime = (EditText) findViewById(R.id.edit_max_time);
                if(mincost.getText().toString().trim().length() == 0 && maxcost.getText().toString().trim().length() == 0 && mintime.getText().toString().trim().length() == 0 && maxtime.getText().toString().trim().length() == 0) {
                    minc = 0;
                    mint = 0;
                    maxc = Double.MAX_VALUE;
                    maxt = Double.MAX_VALUE;
                }
                else {
                    if (mincost.getText().toString().trim().length() == 0) {
                        Toast.makeText(this, "Please input a minimum cost.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (maxcost.getText().toString().trim().length() == 0) {
                        Toast.makeText(this, "Please input a maximum cost.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (mintime.getText().toString().trim().length() == 0) {
                        Toast.makeText(this, "Please input a minimum prep time.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (maxtime.getText().toString().trim().length() == 0) {
                        Toast.makeText(this, "Please input a maximum prep time.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    try {
                        minc = Double.parseDouble(mincost.getText().toString());
                        maxc = Double.parseDouble(maxcost.getText().toString());
                        mint = Double.parseDouble(mintime.getText().toString());
                        maxt = Double.parseDouble(maxtime.getText().toString());
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(this, "Invalid Cost/Time Filter.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                setFilterIngredients();
                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public static double getMinc() { return minc; }
    public static double getMaxc() { return maxc; }
    public static double getMint() { return mint; }
    public static double getMaxt() { return maxt; }

    private static void setFilterIngredients() {
        for(int i = 0; i < FilterAdapter.selectionselected.size(); i++) {
            if(FilterAdapter.selectionselected.get(i)) {
                filteringredients.add(MainActivity.recipebook.getAllUniqueIngredients().get(i));
            }
        }
    }

    public static ArrayList<Ingredient> getFilteringredients() {
        return filteringredients;
    }

    @Override
    public void onStop() {
        super.onStop();
        FilterAdapter.filterselection = 99;
    }
}
