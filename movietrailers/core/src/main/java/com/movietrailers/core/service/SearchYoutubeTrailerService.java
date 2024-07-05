package com.movietrailers.core.service;

import com.movietrailers.core.beans.YoutubeResponseBean;

import java.io.IOException;

/**
 * Interface for defining the Youtube API call
 */
public interface SearchYoutubeTrailerService {
    YoutubeResponseBean callYoutubeSearchForVideoId (String query, int maxResults) throws IOException, InterruptedException;
}
