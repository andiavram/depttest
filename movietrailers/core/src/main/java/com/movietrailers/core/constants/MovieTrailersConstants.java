package com.movietrailers.core.constants;

/**
 * Class for defining the constants used by the Movie Trailers code
 */
public final class MovieTrailersConstants {

    public static final String SEARCH_MOVIE_COMPONENT_PATH = "movietrailers/components/searchmovies";
    public static final String TRAILER_SEARCH_TERM = "+trailer";
    public static final int MAXIMUM_NUMBER_OF_TRAILERS = 3;
    public static final int MOST_RELEVANT_RESULT_INDEX = 0;
    public static final String SPACE = " ";
    public static final String PLUS = "+";
    public static final String QUERY = "query";
    public static final String TMDB_API_CALL_URL_PATH = "https://api.themoviedb.org/3/search/movie?include_adult=false&query=";
    public static final String TMDB_AUTHORIZATION = "Authorization";
    public static final String TMDB_BEARER = "Bearer ";
    public static final String YOUTUBE_SEARCH_API_CALL_URL_PATH = "https://www.googleapis.com/youtube/v3/search?key=";
    public static final String YOUTUBE_SEARCH_API_QUERY_MARKER = "&q=";
    public static final String YOUTUBE_SEARCH_API_MAX_RESULTS_MARKER = "&maxResults=";

    private MovieTrailersConstants() {
    }
}
