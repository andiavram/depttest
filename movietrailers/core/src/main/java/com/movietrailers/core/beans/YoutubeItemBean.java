package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class YoutubeItemBean {

    private String kind;
    private String etag;
    private YoutubeIdBean id;

    public YoutubeItemBean() {
    }

    public YoutubeItemBean(String kind, String etag, YoutubeIdBean id) {
        this.kind = kind;
        this.etag = etag;
        this.id = id;
    }

    public YoutubeItemBean(Builder builder) {
        this.kind = builder.kind;
        this.etag = builder.etag;
        this.id = builder.id;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public YoutubeIdBean getId() {
        return id;
    }

    public static class Builder {
        private String kind;
        private String etag;
        private YoutubeIdBean id;

        public Builder withKind(String kind) {
            this.kind = kind;
            return this;
        }

        public Builder withEtag(String etag) {
            this.etag = etag;
            return this;
        }

        public Builder withId(YoutubeIdBean id) {
            this.id = id;
            return this;
        }

        public YoutubeItemBean build() {return new YoutubeItemBean(this);}
    }
}
