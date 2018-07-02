package com.travis.movie.model;

/**
 * Created by ThisIsNSH on 6/27/2018.
 */

public class Movie {
    String title,img,id;

    public Movie(String title,String img,String id){
        this.title = title;
        this.img = img;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id= id;
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
