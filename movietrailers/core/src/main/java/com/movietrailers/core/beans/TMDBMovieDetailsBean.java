package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMDBMovieDetailsBean {

    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private String id;
    private String original_language;
    private String original_title;
    private String overview;
    private String popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private String vote_average;
    private String vote_count;

    public TMDBMovieDetailsBean() {
    }

    public TMDBMovieDetailsBean(boolean adult, String backdrop_path, List<Integer> genre_ids, String id, String original_language, String original_title, String overview, String popularity, String poster_path, String release_date, String title, boolean video, String vote_average, String vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public TMDBMovieDetailsBean(Builder builder) {
        this.adult = builder.adult;
        this.backdrop_path = builder.backdrop_path;
        this.genre_ids = builder.genre_ids;
        this.id = builder.id;
        this.original_language = builder.original_language;
        this.original_title = builder.original_title;
        this.overview = builder.overview;
        this.popularity = builder.popularity;
        this.poster_path = builder.poster_path;
        this.release_date = builder.release_date;
        this.title = builder.title;
        this.video = builder.video;
        this.vote_average = builder.vote_average;
        this.vote_count = builder.vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public static class Builder {

        private boolean adult;
        private String backdrop_path;
        private List<Integer> genre_ids;
        private String id;
        private String original_language;
        private String original_title;
        private String overview;
        private String popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private boolean video;
        private String vote_average;
        private String vote_count;

        public Builder withAdult(boolean adult) {
            this.adult = adult;
            return this;
        }

        public Builder withBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
            return this;
        }

        public Builder withGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withOriginal_language(String original_language) {
            this.original_language = original_language;
            return this;
        }

        public Builder withOriginal_title(String original_title) {
            this.original_title = original_title;
            return this;
        }

        public Builder withOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public Builder withPopularity(String popularity) {
            this.popularity = popularity;
            return this;
        }

        public Builder withPoster_path(String poster_path) {
            this.poster_path = poster_path;
            return this;
        }

        public Builder withRelease_date(String release_date) {
            this.release_date = release_date;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withVideo(boolean video) {
            this.video = video;
            return this;
        }

        public Builder withVote_average(String vote_average) {
            this.vote_average = vote_average;
            return this;
        }

        public Builder withVote_count(String vote_count) {
            this.vote_count = vote_count;
            return this;
        }

        public TMDBMovieDetailsBean build() {return new TMDBMovieDetailsBean(this);}


    }
}
