package com.rahuljanagouda.popularmoviesone.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rahuljanagouda.popularmoviesone.R;
import com.rahuljanagouda.popularmoviesone.pojo.movie.MovieApiResponse;
import com.rahuljanagouda.popularmoviesone.pojo.movie.Result;
import com.rahuljanagouda.popularmoviesone.ui.DetailsActivity;
import com.rahuljanagouda.popularmoviesone.utils.Network;


/**
 * Created by rahuljanagouda on 14/02/16.
 */
public class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final MovieApiResponse pageResponse;

    public ImageAdapter(Context mContext, MovieApiResponse response) {
        this.mContext = mContext;
        pageResponse = response;
    }

    public int getCount() {
        return pageResponse.getResults().size();
    }

    public Object getItem(int position) {
        return pageResponse.getResults().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (vi == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            vi = layoutInflater.inflate(R.layout.item_card, parent, false);
            holder = new ViewHolder();

            holder.movieThumb = (ImageView) vi.findViewById(R.id.card_image);
            holder.movieName = (TextView) vi.findViewById(R.id.movieName);

            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        final Result movieResult = (Result) getItem(position);

        holder.movieName.setText(movieResult.getTitle());
        if (movieResult.getPosterPath() != null) {
            Glide
                    .with(mContext)
                    .load(Network.TMDB_IMAGE_BASE_URL + movieResult.getPosterPath())
                    .error(R.drawable.placeholder)
                    .into(holder.movieThumb);
        } else {
            Glide
                    .with(mContext)
                    .load(R.drawable.placeholder)
                    .into(holder.movieThumb);
        }

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra("movieResult", movieResult);
                mContext.startActivity(i);

            }
        });

        return vi;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    static class ViewHolder {

        ImageView movieThumb;
        TextView movieName;
    }
}
