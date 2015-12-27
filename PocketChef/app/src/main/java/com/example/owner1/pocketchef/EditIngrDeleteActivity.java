package com.example.owner1.pocketchef;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import javax.xml.transform.Templates;

/**
 * Created by shadow8t4 on 12/5/15.
 *
 */
public class EditIngrDeleteActivity extends AppCompatActivity {

    public static Context context;

    //public static int selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingr_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_ingr_delete_toolbar);
        toolbar.setTitle("Pocket Chef - Edit Recipe (Delete Ingredient)");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        CustomAdapter.selection = 99;

        final CustomAdapter editadapter = new CustomAdapter(this,R.layout.list_item, EditActivity.getTempingr());
        ListView list = (ListView) findViewById(R.id.edit_ingr_delete_list);
        list.setLongClickable(false);
        list.setAdapter(editadapter);

        context = this.getApplicationContext();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                CustomAdapter.selection = position;
                editadapter.notifyDataSetChanged();
                //selection = 99;
            }
        });
    }   //end on create func

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_ingr_delete_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.edit_ingr_delete_confirm:
                if(CustomAdapter.selection != 99) {
                    EditActivity.tempingr.remove(CustomAdapter.selection);
                }
                finish();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        CustomAdapter.selection = 99;
    }
}
