package es.cice.toolbartest;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    private EditText searchET;
    private ActionBar aBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.includedToolbar);
        setSupportActionBar(toolbar);
        aBar=getSupportActionBar();
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
                            String searchText=searchET.getText().toString();
                            Log.d(TAG,"search: " + searchText);
                            InputMethodManager imn=
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imn.hideSoftInputFromWindow(searchET.getWindowToken(),0);
                            aBar.setDisplayShowCustomEnabled(false);
                            aBar.setDisplayShowTitleEnabled(true);
                            //empezar la busqueda
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
                aBar.setDisplayShowCustomEnabled(false);
                aBar.setDisplayShowTitleEnabled(true);
                break;
            case R.id.aboutIT:
                Log.d(TAG,"About item...");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
