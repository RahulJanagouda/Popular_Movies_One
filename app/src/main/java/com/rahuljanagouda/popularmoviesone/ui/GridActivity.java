package com.rahuljanagouda.popularmoviesone.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rahuljanagouda.popularmoviesone.AppController;
import com.rahuljanagouda.popularmoviesone.R;
import com.rahuljanagouda.popularmoviesone.adapters.ImageAdapter;
import com.rahuljanagouda.popularmoviesone.helper.GsonRequest;
import com.rahuljanagouda.popularmoviesone.pojo.MovieApiResponse;
import com.rahuljanagouda.popularmoviesone.utils.Network;

public class GridActivity extends AppCompatActivity {
    private Context mContext;
    private GridView moviesGrid;
    private MovieApiResponse movieApiResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        moviesGrid = (GridView) findViewById(R.id.moview_grid_layout);


        if (savedInstanceState == null || !savedInstanceState.containsKey("MovieApiResponse")) {
            makeNetworkRequest(1);
        } else {
            movieApiResponse = savedInstanceState.getParcelable("MovieApiResponse");
            setupGridView(moviesGrid, movieApiResponse);
        }
    }

    private void makeNetworkRequest(int i) {
        String URL = Network.URL_TMDB_DISCOVER_MOVIES_POPULAR_API;
        if (i == 1) {
            URL = Network.URL_TMDB_DISCOVER_MOVIES_HIGHEST_RATED_API;
        }

        GsonRequest<MovieApiResponse> gsonRequest = new GsonRequest<>(URL, MovieApiResponse.class, new Response.Listener<MovieApiResponse>() {
            @Override
            public void onResponse(MovieApiResponse response) {
                assert moviesGrid != null;
                movieApiResponse = response;
                setupGridView(moviesGrid, movieApiResponse);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setupGridView(moviesGrid, null);
            }
        });


        AppController.getInstance().addToRequestQueue(gsonRequest);
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

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Sort By")
                    .setItems(R.array.sort_by, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            makeNetworkRequest(which);
                        }
                    });
            builder.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupGridView(@NonNull GridView moviesGrid, MovieApiResponse response) {
        moviesGrid.setAdapter(new ImageAdapter(mContext, response));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("MovieApiResponse", movieApiResponse);
        super.onSaveInstanceState(outState);
    }
}
