package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MostRelevantMovieBean {

    private String title;
    private String overview;
    private String language;
    private String releaseDate;
    private String rating;
    private String ratingCount;
    private List<String> videoIds;

    public MostRelevantMovieBean() {
    }

    public MostRelevantMovieBean(String title, String overview, String language, String releaseDate, String rating, String ratingCount, List<String> videoIds) {
        this.title = title;
        this.overview = overview;
        this.language = language;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.videoIds = videoIds;
    }

    public MostRelevantMovieBean(Builder builder) {
        this.title = builder.title;
        this.overview = builder.overview;
        this.language = builder.language;
        this.releaseDate = builder.releaseDate;
        this.rating = builder.rating;
        this.ratingCount = builder.ratingCount;
        this.videoIds = builder.videoIds;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public static class Builder {
        private String title;
        private String overview;
        private String language;
        private String releaseDate;
        private String rating;
        private String ratingCount;
        private List<String> videoIds;

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
        public Builder withLanguage(String language) {
            if(language != null && !language.equals(StringUtils.EMPTY)) {
                this.language = language;
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
        public Builder withRatingCount(String ratingCount) {
            if(ratingCount != null && !ratingCount.equals(StringUtils.EMPTY)) {
                this.ratingCount = ratingCount;
            }
            return this;
        }

        public Builder withVideoIds(List<String> videoIds) {
            if(videoIds != null && !videoIds.isEmpty()) {
                this.videoIds = videoIds;
            }
            return this;
        }

        public MostRelevantMovieBean build() {return new MostRelevantMovieBean(this);}
    }
}
