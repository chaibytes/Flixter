package com.chaibytes.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaibytes.flixter.R;
import com.chaibytes.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.chaibytes.flixter.R.id.tvOverview;
import static com.chaibytes.flixter.R.id.tvTitle;

/**
 * Created by pdebadarshini on 10/15/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        ViewHolder viewholder;

        // check the existing view being reused
        if(convertView == null) {
            viewholder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewholder.tvTitle = (TextView) convertView.findViewById(tvTitle);
            viewholder.tvOverview = (TextView) convertView.findViewById(tvOverview);
            viewholder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

            // cache the viewHolder object inside the fresh view
            convertView.setTag(viewholder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewholder = (ViewHolder) convertView.getTag();
        }

        // clear out image
        viewholder.ivImage.setImageResource(0);
        viewholder.tvTitle.setText(movie.getOriginalTitle());
        viewholder.tvOverview.setText(movie.getOverview());

        // Landscape vs Potrait
        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewholder.ivImage);
        } else {
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewholder.ivImage);
        }

        // return the view
        return convertView;
    }
}
