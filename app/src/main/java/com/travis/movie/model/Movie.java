package com.travis.movie.model;

/**
 * Created by ThisIsNSH on 6/27/2018.
 */

public class Movie {
    String title,img,release;

    public Movie(String title,String img,String release){
        this.title = title;
        this.img = img;
        this.release = release;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getImg() {

        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
