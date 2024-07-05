package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Bean for defining Youtube Page Info Properties
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YoutubePageInfoBean {

    private Integer totalResults;
    private Integer resultsPerPage;

    public YoutubePageInfoBean() {
    }

    public YoutubePageInfoBean(Integer totalResults, Integer resultsPerPage) {
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public YoutubePageInfoBean(Builder builder) {
        this.totalResults = builder.totalResults;
        this.resultsPerPage = builder.resultsPerPage;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public static class Builder {
        private Integer totalResults;
        private Integer resultsPerPage;

        public Builder withTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        public Builder withResultsPerPage(Integer resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
            return this;
        }

        public YoutubePageInfoBean build() {return new YoutubePageInfoBean(this);}
    }
}
