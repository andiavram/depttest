package com.movietrailers.core.service;

import com.movietrailers.core.beans.TMDBResponseBean;

import java.io.IOException;

public interface SearchMoviesService {
    TMDBResponseBean callTMDB (String apiKey, String query) throws IOException, InterruptedException;
}
