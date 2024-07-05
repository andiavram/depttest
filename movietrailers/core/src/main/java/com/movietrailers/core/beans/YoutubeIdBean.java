package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Bean for defining Youtube ID Properties
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YoutubeIdBean {

    private String kind;
    private String videoId;

    public YoutubeIdBean() {
    }

    public YoutubeIdBean(String kind, String videoId) {
        this.kind = kind;
        this.videoId = videoId;
    }

    public YoutubeIdBean(Builder builder) {
        this.kind = builder.kind;
        this.videoId = builder.videoId;
    }

    public String getKind() {
        return kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public static class Builder {
        private String kind;
        private String videoId;

        public Builder withKind(String kind) {
            this.kind = kind;
            return this;
        }

        public Builder withVideoId(String videoId) {
            this.videoId = videoId;
            return this;
        }

        public YoutubeIdBean build() {return new YoutubeIdBean(this);}
    }
}
