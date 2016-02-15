package com.rahuljanagouda.popularmoviesone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.rahuljanagouda.popularmoviesone.adapters.ImageAdapter;
import com.rahuljanagouda.popularmoviesone.utils.Okhttp;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ((TextView) findViewById(R.id.dummy)).setText(new Okhttp().run().getResults().get(0).getOriginalTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ((TextView)findViewById(R.id.dummy)).setText(Network.URL_TMDB_DISCOVER_MOVIES_POPULAR_API);
        GridView moviesGrid = (GridView) findViewById(R.id.moview_grid_layout);

        moviesGrid.setAdapter(new ImageAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
