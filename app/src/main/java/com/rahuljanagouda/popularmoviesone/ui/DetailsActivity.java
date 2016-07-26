package com.rahuljanagouda.popularmoviesone.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.rahuljanagouda.popularmoviesone.AppController;
import com.rahuljanagouda.popularmoviesone.R;
import com.rahuljanagouda.popularmoviesone.helper.GsonRequest;
import com.rahuljanagouda.popularmoviesone.pojo.movie.Result;
import com.rahuljanagouda.popularmoviesone.pojo.videos.VideoResponse;
import com.rahuljanagouda.popularmoviesone.utils.Network;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private Context mContext;
    private Result movieResult;
    public List<com.rahuljanagouda.popularmoviesone.pojo.videos.Result> videoResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;


        if (savedInstanceState == null || !savedInstanceState.containsKey("movieResult")) {
            movieResult = getIntent().getParcelableExtra("movieResult");
        } else {
            movieResult = savedInstanceState.getParcelable("movieResult");
        }

        String URL = Network.TMDB_VIDEO_URL.replace("{id}",String.valueOf(movieResult.getId()));

        GsonRequest<VideoResponse> gsonRequest = new GsonRequest<>(URL, VideoResponse.class, new Response.Listener<VideoResponse>() {
            @Override
            public void onResponse(VideoResponse response) {
                videoResult = response.getResults();

                TextView movieVideos = (TextView) findViewById(R.id.movieVideos);
                movieVideos.setText(videoResult.get(0).getName());
                movieVideos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Network.watchYoutubeVideo(mContext,videoResult.get(0).getKey());
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                videoResult = null;

                TextView movieVideos = (TextView) findViewById(R.id.movieVideos);
                movieVideos.setVisibility(View.GONE);
            }
        });
        AppController.getInstance().addToRequestQueue(gsonRequest);


        ImageView movieThumb = (ImageView) findViewById(R.id.movieThumb);
        TextView movieOverview = (TextView) findViewById(R.id.movieOverview);
        TextView movieRleaseDate = (TextView) findViewById(R.id.movieRleaseDate);
        TextView movieRating = (TextView) findViewById(R.id.movieRating);



        if (movieResult.getPosterPath() != null) {
            Glide
                .with(mContext)
                .load(Network.TMDB_IMAGE_HQ_BASE_URL + movieResult.getPosterPath())
                .error(R.drawable.placeholder)
                .into(movieThumb);

        } else {
            Glide
                .with(mContext)
                .load(R.drawable.placeholder)
                .into(movieThumb);
        }
        movieOverview.setText(movieResult.getOverview());
        movieRleaseDate.setText(movieResult.getReleaseDate());
        movieRating.setText(String.valueOf(movieResult.getVoteAverage()));

        toolbar.setTitle(movieResult.getTitle());
        getSupportActionBar().setTitle(movieResult.getTitle());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movieResult", movieResult);
        super.onSaveInstanceState(outState);
    }
}
