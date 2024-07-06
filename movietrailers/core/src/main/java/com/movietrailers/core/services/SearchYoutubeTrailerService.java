package com.movietrailers.core.services;

import com.movietrailers.core.beans.YoutubeResponseBean;

import java.io.IOException;

/**
 * Interface for defining the Youtube API call
 */
public interface SearchYoutubeTrailerService {
    YoutubeResponseBean callYoutubeSearchForVideoId (String query, int maxResults) throws IOException, InterruptedException;
}
