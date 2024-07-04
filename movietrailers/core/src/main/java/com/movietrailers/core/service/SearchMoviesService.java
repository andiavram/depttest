package com.movietrailers.core.service;

import com.movietrailers.core.beans.TMDBResponseBean;

import java.io.IOException;

public interface SearchMoviesService {
    TMDBResponseBean callTMDB (String apiKey) throws IOException, InterruptedException;
}
