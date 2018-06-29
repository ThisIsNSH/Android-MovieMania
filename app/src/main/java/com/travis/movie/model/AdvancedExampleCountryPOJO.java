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
                new AdvancedExampleCountryPOJO("2018"),
                new AdvancedExampleCountryPOJO("2017"),
                new AdvancedExampleCountryPOJO("2016"),
                new AdvancedExampleCountryPOJO("2015"),
                new AdvancedExampleCountryPOJO("2014"),
                new AdvancedExampleCountryPOJO("2013"),
                new AdvancedExampleCountryPOJO("2012"),
                new AdvancedExampleCountryPOJO("2011"),
                new AdvancedExampleCountryPOJO("2010"),
                new AdvancedExampleCountryPOJO("2009")
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