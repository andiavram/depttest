package com.movietrailers.core.service;

import com.movietrailers.core.beans.TMDBResponseBean;

import java.io.IOException;

/**
 * Interface for defining the TMDB API call
 */
public interface SearchMoviesService {
    TMDBResponseBean callTMDB (String query) throws IOException, InterruptedException;
}
