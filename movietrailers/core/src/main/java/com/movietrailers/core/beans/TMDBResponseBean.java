package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMDBResponseBean {

    private Integer page;
    private List<TMDBMovieDetailsBean> results;
    private Integer total_pages;
    private Integer total_results;

    public TMDBResponseBean() {
    }

    public TMDBResponseBean(Integer page, List<TMDBMovieDetailsBean> results, Integer total_pages, Integer total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public TMDBResponseBean(Builder builder) {
        this.page = builder.page;
        this.results = builder.results;
        this.total_pages = builder.total_pages;
        this.total_results = builder.total_results;
    }

    public Integer getPage() {
        return page;
    }

    public List<TMDBMovieDetailsBean> getResults() {
        return results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public static class Builder {
        private Integer page;
        private List<TMDBMovieDetailsBean> results;
        private Integer total_pages;
        private Integer total_results;

        public Builder withPage(Integer page) {
            this.page = page;
            return this;
        }

        public Builder withResults(List<TMDBMovieDetailsBean> results) {
            this.results = results;
            return this;
        }

        public Builder withTotal_pages(Integer total_pages) {
            this.total_pages = total_pages;
            return this;
        }

        public Builder withTotal_results(Integer total_results) {
            this.total_results = total_results;
            return this;
        }

        public TMDBResponseBean build() {return new TMDBResponseBean(this);}
    }
}
