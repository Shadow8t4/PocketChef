package com.example.owner1.pocketchef;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Shadow8t4 on 12/6/2015.
 *
 */

public class FridgeAddActivity extends AppCompatActivity {

    public Ingredient ingr = new Ingredient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_fridge_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fridge_add_toolbar);
        toolbar.setTitle("Pocket Chef - Fridge - Add Ingredient");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fridge_add_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        EditText name = (EditText) findViewById(R.id.fridge_ingr_name_edit);
        EditText unit = (EditText) findViewById(R.id.fridge_ingr_unit_edit);
        EditText cost = (EditText) findViewById(R.id.fridge_ingr_cost_edit);
        EditText amount = (EditText) findViewById(R.id.fridge_ingr_amount_edit);

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.fridge_add_confirm:
                try {
                    ingr.setCost(Double.parseDouble(cost.getText().toString()));
                    ingr.setQuantity(Double.parseDouble(amount.getText().toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid cost or quantity format.", Toast.LENGTH_SHORT).show();
                    break;
                }
                ingr.setName(name.getText().toString());
                ingr.setUnit(unit.getText().toString());
                FridgeActivity.fridge.addIngredient(ingr);

                FridgeActivity.fridgexmlreader.writeXML(FridgeActivity.fridge, FridgeActivity.filename, this);

                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
