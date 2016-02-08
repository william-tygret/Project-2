package com.example.williamtygret.project2neighborhood;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
     ListView mListView;
     CursorAdapter mCursorAdapter;
    DatabaseHelper mHelper;

    EditText mEditText;
    Button mSearchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);
        mListView = (ListView)findViewById(R.id.listView);
        Cursor cursor = DatabaseHelper.getInstance(this).getPlacesList();
        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{DatabaseHelper.COL_PLACE_NAME},new int[]{android.R.id.text1},0);
        mListView.setAdapter(mCursorAdapter);

        mEditText = (EditText)findViewById(R.id.edit_text);
        mSearchButton = (Button)findViewById(R.id.button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mEditText.getText().toString();
                mHelper = DatabaseHelper.getInstance(MainActivity.this);
                Cursor cursor = mHelper.searchPlaces(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mCursorAdapter.getCursor();
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                cursor.moveToPosition(position);
                int theID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                intent.putExtra("id", theID);
                startActivity(intent);
            }
        });


       handleIntent(getIntent());


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d("MainActivity","Handling Intent");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            // where you do the actual database search
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "WE GOT THAT SHIT!! THAT DIRRTY OL SHIT FROM UR SEARCH LINE!! " + query, Toast.LENGTH_SHORT).show();

            Cursor cursor = mHelper.getInstance(this).searchPlaces(query);

            //DatabaseHelper helper = DatabaseHelper.getInstance(this);
            mCursorAdapter.swapCursor(cursor);
            mCursorAdapter.notifyDataSetChanged();
        }
    }

}
