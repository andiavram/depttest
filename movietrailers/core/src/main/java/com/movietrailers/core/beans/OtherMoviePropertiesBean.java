package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

/**
 * Bean for defining the Other Movies Properties
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtherMoviePropertiesBean {

    private String title;
    private String overview;
    private String releaseDate;
    private String rating;

    public OtherMoviePropertiesBean() {
    }

    public OtherMoviePropertiesBean(String title, String overview, String releaseDate, String rating) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public OtherMoviePropertiesBean(Builder builder) {
        this.title = builder.title;
        this.overview = builder.overview;
        this.releaseDate = builder.releaseDate;
        this.rating = builder.rating;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public static class Builder {
        private String title;
        private String overview;
        private String releaseDate;
        private String rating;

        public Builder withTitle(String title) {
            if(title != null && !title.equals(StringUtils.EMPTY)) {
                this.title = title;
            }
            return this;
        }

        public Builder withOverview(String overview) {
            if(overview != null && !overview.equals(StringUtils.EMPTY)) {
                this.overview = overview;
            }
            return this;
        }
        public Builder withReleaseDate(String releaseDate) {
            if(releaseDate != null && !releaseDate.equals(StringUtils.EMPTY)) {
                this.releaseDate = releaseDate;
            }
            return this;
        }
        public Builder withRating(String rating) {
            if(rating != null && !rating.equals(StringUtils.EMPTY)) {
                this.rating = rating;
            }
            return this;
        }

        public OtherMoviePropertiesBean build() {return new OtherMoviePropertiesBean(this);}
    }
}
