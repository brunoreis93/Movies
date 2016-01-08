package com.example.android.bestmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bruninho on 07/01/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private Context context;
    public  MovieAdapter(Context context, ArrayList<Movie> movie){
        super(context, 0 ,movie);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_best_movie, parent, false);
        }

        String poster = movie.getPoster();
        String baseUrlImage = "http://image.tmdb.org/t/p/";
        String imageSize = "w342/";
        String imageFile = baseUrlImage + imageSize + poster;

        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        Picasso.with(context).load(imageFile).into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.rate);
        textView.setText(Double.toString(movie.getVoteAverage()) + "/10.0");
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
        titleTextView.setText(movie.getTitle());

        return convertView;
    }
}
