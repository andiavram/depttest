package com.movietrailers.core.services;

import com.movietrailers.core.beans.TMDBResponseBean;

import java.io.IOException;

/**
 * Interface for defining the TMDB API call
 */
public interface SearchTMDBMoviesService {
    TMDBResponseBean callTMDB (String query) throws IOException, InterruptedException;
}
