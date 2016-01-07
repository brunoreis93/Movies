package com.example.android.bestmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruninho on 05/01/2016.
 */
public class FetchMoviesTask extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    public MainActivityFragment mainActivityFragment;

    private ArrayList<Movie> getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {

        final String IMDB_RESULTS = "results";
        final String IMDB_BACKDROP = "backdrop_path";
        final String IMDB_ORIGINAL_TITLE = "original_title";
        final String IMDB_OVERVIEW = "overview";
        final String IMDB_POSTER = "poster_path";
        final String IMDB_TITLE = "title";
        final String IMDB_ID = "id";
        final String IMDB_RELEASE = "release_date";

        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray movieArray = moviesJson.getJSONArray(IMDB_RESULTS);

        ArrayList<Movie> resultsOfMovies = new ArrayList<>();
        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);

            Movie movie = new Movie();

            movie.setTitle(movieObject.getString(IMDB_TITLE));
            movie.setBackdrop(movieObject.getString(IMDB_BACKDROP));
            movie.setOriginalTitle(movieObject.getString(IMDB_ORIGINAL_TITLE));
            movie.setOverview(movieObject.getString(IMDB_OVERVIEW));
            movie.setPoster(movieObject.getString(IMDB_POSTER));
            movie.setId(movieObject.getInt(IMDB_ID));
            movie.setReleaseDate(movieObject.getString(IMDB_RELEASE));


            resultsOfMovies.add(movie);
        }

        return resultsOfMovies;
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... params){
        Log.v("DoInBackground", "working");
        /*if (params.length == 0) {
            Log.v("first condition", "o caralho está vazio");
            return null;
        }*/

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        //Will contain the raw JSON response as a String.
        String moviesJsonStr = null;
        String sortByValue = "popularity.desc";
        int pageValue = 1;

        try {

            Log.v("Try point", "working");
            final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String PAGE_PARAM = "page";
            final String KEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, sortByValue)
                    .appendQueryParameter(PAGE_PARAM, Integer.toString(pageValue))
                    .appendQueryParameter(KEY_PARAM, BuildConfig.MOVIES_KEY)
                    .build();

            Log.v("LOOK HERE", builtUri.toString());

            URL url = new URL(builtUri.toString());

            //Create the request to IMDB and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                moviesJsonStr = null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                moviesJsonStr = null;
            }
            moviesJsonStr = buffer.toString();



        } catch (IOException e){
            Log.e("CATCH POINT", "EXCEPTION HAPPENING");
            moviesJsonStr = null;
        }finally {
            if (urlConnection != null){
                try{
                    reader.close();
                } catch (IOException e){
                    Log.e(LOG_TAG,"Error closing the connection");
                }
            }
        }
        try {
            return getMoviesDataFromJson(moviesJsonStr);
        }catch (JSONException e){
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        if (movies != null){
            this.mainActivityFragment.adapterOnMovies(movies);
        }
    }
}
