package com.example.assginment.model;

import java.util.List;

public class Section {
    private String title;
    private List<Movie> listMovie;

    public Section(String title, List<Movie> listMovie) {
        this.title = title;
        this.listMovie = listMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }
}
