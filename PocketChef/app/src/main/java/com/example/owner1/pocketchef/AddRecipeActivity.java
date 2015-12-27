package com.example.owner1.pocketchef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class AddRecipeActivity extends AppCompatActivity {

    private static Recipe r = new Recipe();
    private static TextView ingr_list;

    private static EditText name;
    private static EditText app;
    private static EditText prep;
    private static EditText inst;
    private static EditText u;

    private static String tempname;
    private static String tempurl;
    private static String temptime;
    private static String tempapp;
    private static String tempinst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        toolbar.setTitle("Pocket Chef - Add Recipe");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        name = (EditText) findViewById(R.id.name_edit);
        prep = (EditText) findViewById(R.id.prep_edit);
        app = (EditText) findViewById(R.id.app_edit);
        inst = (EditText) findViewById(R.id.inst_edit);
        u = (EditText) findViewById(R.id.u_edit);

        ingr_list = (TextView) findViewById(R.id.ingr_list);
        if(!r.getIngredients().isEmpty()) {
            String tempingr = "";

            for(int i = 0; i < r.getIngredients().size(); i++) {
                tempingr += r.getIngredients().get(i).toString();
                if(i != r.getIngredients().size() - 1) {
                    tempingr += "\n";
                }
            }

            ingr_list.setText(tempingr);
        }
        else
            ingr_list.setText(R.string.none);
        //

        Button button = (Button) findViewById(R.id.ingr_button);

        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                tempname = name.getText().toString();
                tempapp = app.getText().toString();
                tempinst = inst.getText().toString();
                temptime = prep.getText().toString();
                tempurl = u.getText().toString();
                AddIngrActivity(view);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        name = (EditText) findViewById(R.id.name_edit);
        prep = (EditText) findViewById(R.id.prep_edit);
        app = (EditText) findViewById(R.id.app_edit);
        inst = (EditText) findViewById(R.id.inst_edit);
        u = (EditText) findViewById(R.id.u_edit);

        name.setText(tempname);
        prep.setText(temptime);
        app.setText(tempapp);
        inst.setText(tempinst);
        u.setText(tempurl);

        if(!r.getIngredients().isEmpty()) {
            String tempingr = "";

            for(int i = 0; i < r.getIngredients().size(); i++) {
                tempingr += r.getIngredients().get(i).toString();
                if(i != r.getIngredients().size() - 1) {
                    tempingr += "\n";
                }
            }

            ingr_list.setText(tempingr);
        }
        else
            ingr_list.setText(R.string.none);
    }

    public static ArrayList<Ingredient> getRecipeList() {
        return r.getIngredients();
    }

    public static void addRecipeIngredients(Ingredient ingr) {
        r.addIngredient(ingr);
    }

    public static void removeRecipeIngredients(int i) {
        r.getIngredients().remove(i);
    }

    public void AddIngrActivity(View view) {
        Intent intent = new Intent(this, AddIngrActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.add_confirm:
                try {
                    URL url = new URL(u.getText().toString());
                } catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                    Toast.makeText(this, "Invalid URL.", Toast.LENGTH_SHORT).show();
                    break;
                }
                r.setName(name.getText().toString());
                //r.setIngredients(ingr.getText().toString());
                try {
                    r.setTime(Double.parseDouble(prep.getText().toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Incorrect prep time format.", Toast.LENGTH_SHORT).show();
                    break;
                }
                r.setAppliance(app.getText().toString());
                r.setText(new ArrayList<>(Arrays.asList(inst.getText().toString().split("\\r?\\n"))));
                r.setURL(u.getText().toString());
                MainActivity.recipebook = MainActivity.xmlreader.readXML(MainActivity.fileName, this);
                MainActivity.recipebook.addRecipe(r);
                r = new Recipe();
                tempname = "";
                tempurl = "";
                tempapp = "";
                tempinst = "";
                temptime = "";

                MainActivity.xmlreader.writeXML(MainActivity.recipebook, MainActivity.fileName, this);

                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

}
