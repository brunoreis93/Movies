package com.example.android.bestmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.internal.widget.ListViewCompat;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public ArrayAdapter<String> mMoviesAdapter;

    public ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.v("TESTE", "just arielando");
        if (id == R.id.action_settings){
            Log.v("TESTE", "just seeing");
            FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
            fetchMoviesTask.mainActivityFragment = this;
            fetchMoviesTask.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.mainActivityFragment = this;
        fetchMoviesTask.execute();
    }

    public void adapterOnMovies (ArrayList<Movie> moviesList){
        movies = moviesList;
        if (mMoviesAdapter != null) {
            mMoviesAdapter.clear();
            for (Movie movie : movies) {
                mMoviesAdapter.add(movie.getTitle());
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        String[] listOfMovies = new String[]{
                "updating..."
        };

        List<String> listMovies = new ArrayList<>(Arrays.asList(listOfMovies));

        mMoviesAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.list_best_movie,
                R.id.list_best_movie_textview,
                listMovies
                //new ArrayList<String>()
        );

        ListView listView = (ListView) rootView.findViewById(R.id.list_best_movie);
        listView.setAdapter(mMoviesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String test = movies.get(position).getReleaseDate();
                Toast toast = Toast.makeText(getContext(), test, Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        return rootView;
    }
}