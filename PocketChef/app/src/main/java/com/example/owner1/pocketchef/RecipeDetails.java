package com.example.owner1.pocketchef;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pocket Chef - Details");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView t1 = (TextView) findViewById(R.id.name_text);
        t1.setText(MainActivity.details.getName());
        TextView t2 = (TextView) findViewById(R.id.ingr_text);

        String tempingr = "";

        for(int i = 0; i < MainActivity.details.getIngredients().size(); i++) {
            tempingr += MainActivity.details.getIngredients().get(i).toString();
            if(i != MainActivity.details.getIngredients().size() - 1) {
                tempingr += "\n";
            }
        }

        t2.setText(tempingr);

        TextView cost = (TextView) findViewById(R.id.cost_text);
        cost.setText(String.format("$%.2f", MainActivity.details.getCost()));

        TextView t3 = (TextView) findViewById(R.id.prep_text);
        if(MainActivity.details.getTime()%1 == 0)
            t3.setText(String.format("%d minutes", ((int) MainActivity.details.getTime())));
        else
            t3.setText(String.format("%.2f minutes", MainActivity.details.getTime()));
        TextView t4 = (TextView) findViewById(R.id.app_text);
        t4.setText(MainActivity.details.getAppliance());
        TextView t5 = (TextView) findViewById(R.id.inst_text);
        String temp = "";
        for(int i = 0; i < MainActivity.details.getText().size(); ++i) {
            temp += MainActivity.details.getText().get(i) + "\n";
        }
        t5.setText(temp);

        new GetImage((ImageView) findViewById(R.id.details_image)).execute(MainActivity.details.getURL());
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView t1 = (TextView) findViewById(R.id.name_text);
        t1.setText(MainActivity.details.getName());
        TextView t2 = (TextView) findViewById(R.id.ingr_text);

        String tempingr = "";

        for(int i = 0; i < MainActivity.details.getIngredients().size(); i++) {
            tempingr += MainActivity.details.getIngredients().get(i).toString();
            if(i != MainActivity.details.getIngredients().size() - 1) {
                tempingr += "\n";
            }
        }

        t2.setText(tempingr);

        TextView cost = (TextView) findViewById(R.id.cost_text);
        cost.setText(String.format("$%.2f", MainActivity.details.getCost()));

        TextView t3 = (TextView) findViewById(R.id.prep_text);
        if(MainActivity.details.getTime()%1 == 0)
            t3.setText(String.format("%d minutes", ((int) MainActivity.details.getTime())));
        else
            t3.setText(String.format("%.2f minutes", MainActivity.details.getTime()));
        TextView t4 = (TextView) findViewById(R.id.app_text);
        t4.setText(MainActivity.details.getAppliance());
        TextView t5 = (TextView) findViewById(R.id.inst_text);
        String temp = "";
        for(int i = 0; i < MainActivity.details.getText().size(); ++i) {
            temp += (i + 1) + ". " + MainActivity.details.getText().get(i) + "\n";
        }
        t5.setText(temp);

        new GetImage((ImageView) findViewById(R.id.details_image)).execute(MainActivity.details.getURL());
    }

    public void EditActivity(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

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
            case R.id.delete:
                MainActivity.recipebook.getRecipes().remove(MainActivity.recipebook.getRecipes().indexOf(MainActivity.details));
                MainActivity.xmlreader.writeXML(MainActivity.recipebook, MainActivity.fileName, this);
                finish();
                break;
            case R.id.edit:
                EditActivity(findViewById(R.id.edit));
                EditActivity.tempingr = new ArrayList<>(MainActivity.details.getIngredients());
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

}
