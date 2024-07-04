package com.movietrailers.core.service;

import com.movietrailers.core.beans.YoutubeResponseBean;

import java.io.IOException;

public interface SearchYoutubeTrailerService {
    YoutubeResponseBean callYoutubeSearchForVideoId (String apiKey, String query) throws IOException, InterruptedException;
}
