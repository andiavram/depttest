package com.movietrailers.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class YoutubeResponseBean {

    private String kind;
    private String etag;
    private String nextPageToken;
    private String regionCode;
    private YoutubePageInfoBean pageInfo;
    private List<YoutubeItemBean> items;

    public YoutubeResponseBean() {
    }

    public YoutubeResponseBean(String kind, String etag, String nextPageToken, String regionCode, YoutubePageInfoBean pageInfo, List<YoutubeItemBean> items) {
        this.kind = kind;
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.regionCode = regionCode;
        this.pageInfo = pageInfo;
        this.items = items;
    }

    public YoutubeResponseBean(Builder builder) {
        this.kind = builder.kind;
        this.etag = builder.etag;
        this.nextPageToken = builder.nextPageToken;
        this.regionCode = builder.regionCode;
        this.pageInfo = builder.pageInfo;
        this.items = builder.items;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public YoutubePageInfoBean getPageInfo() {
        return pageInfo;
    }

    public List<YoutubeItemBean> getItems() {
        return items;
    }

    public static class Builder {
        private String kind;
        private String etag;
        private String nextPageToken;
        private String regionCode;
        private YoutubePageInfoBean pageInfo;
        private List<YoutubeItemBean> items;

        public Builder withKind(String kind) {
            this.kind = kind;
            return this;
        }
        public Builder withEtag(String etag) {
            this.etag = etag;
            return this;
        }
        public Builder withNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
            return this;
        }
        public Builder withRegionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        }

        public Builder withPageInfo(YoutubePageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        public Builder withItems(List<YoutubeItemBean> items) {
            this.items = items;
            return this;
        }

        public YoutubeResponseBean build() {return new YoutubeResponseBean(this);}
    }
}
