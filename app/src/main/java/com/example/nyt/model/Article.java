package com.example.nyt.model; // <============= CHANGE ME

import com.google.gson.annotations.SerializedName;

import java.util.List;

/***
 * Model class for news articles. This should be 100% familiar to you.
 *
 * Week 6
 *  This class now matches what an Article is represented as in the New York Times Most Viewed API.
 */
public class Article {

    // We use a long because the number they use for ID is too big for an int
    private String id;
    private String name;
    private String temperament;
    private String life_span;
    private String wikipedia_url;
    private String origin;
    private String weight_imperial;
    private String dog_friendly;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getOrigin() {
        return origin;
    }

    public String getWeight_imperial() {
        return weight_imperial;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }


}