package com.example.android.bestmovies;

/**
 * Created by Bruninho on 06/01/2016.
 */

//playing with the code with Ju
public class Movie {
    private String mBackdrop;
    private String mOriginalTitle;
    private String mTitle;
    private String mOverview;
    private String mPoster;
    private int mId;
    private String mReleaseDate;

    /**
    *Setting attributes of the class
     */
    //set and get of backDrop
    public void setBackdrop (String backdrop){
        this.mBackdrop = backdrop;
    }

    public String getBackdrop(){
        return mBackdrop;
    }

    public void setOriginalTitle(String originalTitle){
        this.mOriginalTitle = originalTitle;
    }

    public String getOriginalTitle(){
        return mOriginalTitle;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setOverview(String overview){
        this.mOverview = overview;
    }

    public String getOverview(){
        return mOverview;
    }

    public void setPoster (String poster){
        this.mPoster = poster;
    }

    public String getPoster(){
        return mPoster;
    }

    public void setId (int id){
        this.mId = id;
    }

    public int getId(){
        return mId;
    }

    public void setReleaseDate(String releaseDate){
        this.mReleaseDate = releaseDate;
    }

    public String getReleaseDate(){
        return mReleaseDate;
    }
}
