package es.cice.toolbartest;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.cice.toolbartest.adapters.CarAdapter;
import es.cice.toolbartest.model.Car;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    private EditText searchET;
    private ActionBar aBar;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.includedToolbar);
        setSupportActionBar(toolbar);
        aBar=getSupportActionBar();
        RecyclerView carRV= (RecyclerView) findViewById(R.id.carRV);
        adapter=new CarAdapter(this,getData());
        carRV.setAdapter(adapter);
        carRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"onCreateOptionsMenu()...");
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.searchIT:
                Log.d(TAG,"Search item...");
                aBar.setDisplayShowCustomEnabled(true);
                aBar.setCustomView(R.layout.search_layout);
                aBar.setDisplayShowTitleEnabled(false);
                searchET=
                        (EditText) aBar.getCustomView().findViewById(R.id.searchET);
                searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
                        if(action==EditorInfo.IME_ACTION_SEARCH){
                            CharSequence searchText=searchET.getText();
                            Log.d(TAG,"search: " + searchText);
                            InputMethodManager imn=
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imn.hideSoftInputFromWindow(searchET.getWindowToken(),0);
                            aBar.setDisplayShowCustomEnabled(false);
                            aBar.setDisplayShowTitleEnabled(true);
                            //empezar la busqueda
                            adapter.getFilter().filter(searchText);
                            return true;
                        }
                        return false;
                    }
                });

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchET, InputMethodManager.SHOW_IMPLICIT);
                searchET.requestFocus();
                break;
            case R.id.settingIT:
                Log.d(TAG,"Settings item...");

                break;
            case R.id.aboutIT:
                Log.d(TAG,"About item...");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Car> getData() {
        List<Car> list=new ArrayList<>();
        list.add(new Car("bla bla bla","Peugeot",R.drawable.vehiculo1,R.drawable.vehiculo1_thumb,
                "307"));
        list.add(new Car("bla bla bla","Renault",R.drawable.vehiculo2,R.drawable.vehiculo2_thumb,
                "Megane"));
        list.add(new Car("bla bla bla","Peugeot",R.drawable.vehiculo3,R.drawable.vehiculo3_thumb,
                "3008"));
        list.add(new Car("bla bla bla","MVW",R.drawable.vehiculo4,R.drawable.vevhiculo4_thumb,
                "401"));
        list.add(new Car("bla bla bla","Peugeot",R.drawable.vehiculo5,R.drawable.vehiculo5_thumb,
                "407"));
        return list;
    }
}
