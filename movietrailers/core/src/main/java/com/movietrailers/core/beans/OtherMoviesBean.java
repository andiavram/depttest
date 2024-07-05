package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * Bean for defining the Other Movies List
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtherMoviesBean {

    private List<OtherMoviePropertiesBean> otherMovies;

    public OtherMoviesBean() {
    }

    public OtherMoviesBean(List<OtherMoviePropertiesBean> otherMovies) {
        this.otherMovies = otherMovies;
    }

    public OtherMoviesBean(Builder builder) {
        this.otherMovies = builder.otherMovies;
    }

    public List<OtherMoviePropertiesBean> getOtherMovies() {
        return otherMovies;
    }

    public static class Builder {
        private List<OtherMoviePropertiesBean> otherMovies;

        public Builder withOtherMovies(List<OtherMoviePropertiesBean> otherMovies) {
            if(otherMovies != null && !otherMovies.isEmpty()) {
                this.otherMovies = otherMovies;
            }
            return this;
        }

        public OtherMoviesBean build() {return new OtherMoviesBean(this);}
    }
}
