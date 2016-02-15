package com.rahuljanagouda.popularmoviesone.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rahuljanagouda.popularmoviesone.DetailsActivity;
import com.rahuljanagouda.popularmoviesone.R;


/**
 * Created by rahuljanagouda on 14/02/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder,
            R.drawable.placeholder, R.drawable.placeholder
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_card, parent, false);

        } else {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DetailsActivity.class));

            }
        });

        return view;
    }
}
