package com.example.android.bestmovies;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String title = bundle.getString("title");
            String backdrop = bundle.getString("backdrop");
            String originalTitle = bundle.getString("originalTitle");
            String overview = bundle.getString("overview");
            String poster = bundle.getString("poster");
            String baseUrlImage = "http://image.tmdb.org/t/p/";
            String imageSize = "w342/";
            String imageFile = baseUrlImage + imageSize + poster;
            int movieId = bundle.getInt("movieId");
            String releaseDate = bundle.getString("releaseDate");

            TextView titleText = (TextView) rootView.findViewById(R.id.title_text_view);
            TextView overviewText = (TextView) rootView.findViewById(R.id.overview_text_view);
            ImageView postImage = (ImageView) rootView.findViewById(R.id.image_poster);

            titleText.setText(title);
            overviewText.setText(overview);
            Picasso.with(getContext()).load(imageFile).into(postImage);

        }


        return rootView;
    }
}
