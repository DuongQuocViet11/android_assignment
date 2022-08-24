package com.example.assginment.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private String thumbnail;
    private String description;
    private Director director;

    public Movie(String name, String thumbnail, String description, Director director) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.director = director;
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
