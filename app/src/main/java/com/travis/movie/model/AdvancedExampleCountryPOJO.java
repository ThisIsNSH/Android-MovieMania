package com.travis.movie.model;

import com.travis.movie.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ThisIsNSH on 6/28/2018.
 */
public class AdvancedExampleCountryPOJO {
    private String title;

    public AdvancedExampleCountryPOJO(String title) {
        this.title = title;
    }

    public static List<AdvancedExampleCountryPOJO> getExampleDataset() {
        return Arrays.asList(
                new AdvancedExampleCountryPOJO("2018 Movies"),
                new AdvancedExampleCountryPOJO("2017 Movies"),
                new AdvancedExampleCountryPOJO("2016 Movies (No Data for this)"),
                new AdvancedExampleCountryPOJO("2015 Movies"),
                new AdvancedExampleCountryPOJO("2014 Movies"),
                new AdvancedExampleCountryPOJO("2013 Movies"),
                new AdvancedExampleCountryPOJO("2012 Movies")

        );
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getters, setters, equal, hashcode, etc.
}