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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shadow8t4 on 11/25/15.
 *
 */
public class EditActivity extends AppCompatActivity {

    private EditText name;
    public static ArrayList<Ingredient> tempingr;
    private EditText prep;
    private EditText app;
    private EditText inst;
    private EditText u;


    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        // Always call super on override methods.
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        toolbar.setTitle("Pocket Chef - Edit Recipe");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        name = (EditText) findViewById(R.id.name_edit2);
        prep = (EditText) findViewById(R.id.prep_edit2);
        app = (EditText) findViewById(R.id.app_edit2);
        inst = (EditText) findViewById(R.id.inst_edit2);
        u = (EditText) findViewById(R.id.u_edit2);
        name.setText(MainActivity.details.getName());

        TextView ingr = (TextView) findViewById(R.id.edit_ingr_list);

        if(tempingr.isEmpty())
            ingr.setText(R.string.none);
        else {
            String tempingrstring = "";
            for(int i = 0; i < tempingr.size(); i++) {
                tempingrstring += tempingr.get(i).toString();
                if(i != tempingr.size() - 1) {
                    tempingrstring += "\n";
                }
            }
            ingr.setText(tempingrstring);
        }

        if(MainActivity.details.getTime() % 1 == 0)
            prep.setText(String.format("%d", ((int) MainActivity.details.getTime())));
        else
            prep.setText(String.format("%.2f", MainActivity.details.getTime()));

        app.setText(MainActivity.details.getAppliance());

        String temp = "";

        for(int i = 0; i < MainActivity.details.getText().size(); i++) {
            temp += MainActivity.details.getText().get(i);
            if(i != 1) {
                temp += "\n";
            }
        }

        inst.setText(temp);

        u.setText(MainActivity.details.getURL());

        Button button = (Button) findViewById(R.id.edit_ingr_button);

        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                EditIngrActivity(view);
            }
        });

        new GetImage((ImageView) findViewById(R.id.edit_image)).execute(MainActivity.details.getURL());
    }

    public static ArrayList<Ingredient> getTempingr() {
        return tempingr;
    }

    public static void addTempingr(Ingredient ingr) {
        tempingr.add(ingr);
    }

    public void EditIngrActivity(View view) {
        Intent intent = new Intent(this, EditIngrActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);

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
            case R.id.confirm:
                try {
                    URL url = new URL(u.getText().toString());
                } catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                    Toast.makeText(this, "Invalid URL.", Toast.LENGTH_SHORT).show();
                    break;
                }
                int index = MainActivity.recipebook.getRecipes().indexOf(MainActivity.details);
                Recipe r = MainActivity.recipebook.getRecipes().get(index);
                r.setName(name.getText().toString());
                r.setIngredients(tempingr);
                try {
                    r.setTime(Double.parseDouble(prep.getText().toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid prep time.", Toast.LENGTH_SHORT).show();
                    break;
                }
                r.setAppliance(app.getText().toString());
                r.setText(new ArrayList<>(Arrays.asList(inst.getText().toString().split("\\r?\\n"))));
                r.setURL(u.getText().toString());
                MainActivity.recipebook.getRecipes().set(index, r);
                MainActivity.xmlreader.writeXML(MainActivity.recipebook, MainActivity.fileName, this);
                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
