package com.travis.movie.model;

import com.travis.movie.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ThisIsNSH on 6/28/2018.
 */
public class AdvancedExampleCountryPOJO {
    private String title;
    private int icon;

    public AdvancedExampleCountryPOJO(String title, int icon) {
        this.title = title;
        this.icon = icon;

    }

    public static List<AdvancedExampleCountryPOJO> getExampleDataset() {
        return Arrays.asList(
                new AdvancedExampleCountryPOJO("Russian Federation", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Canada", R.drawable.sample),
                new AdvancedExampleCountryPOJO("United States of America", R.drawable.sample),
                new AdvancedExampleCountryPOJO("China", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Brazil", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Australia", R.drawable.sample),
                new AdvancedExampleCountryPOJO("India", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Argentina", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Kazakhstan", R.drawable.sample),
                new AdvancedExampleCountryPOJO("Algeria", R.drawable.sample)
        );
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getters, setters, equal, hashcode, etc.
}