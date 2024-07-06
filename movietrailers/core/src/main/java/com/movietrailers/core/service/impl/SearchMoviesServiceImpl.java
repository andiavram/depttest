package com.movietrailers.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import com.movietrailers.core.service.TMDBConfigService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.movietrailers.core.constants.MovieTrailersConstants.*;

/**
 * Class for defining the TMDB API call
 */
@Component(service = SearchMoviesService.class)
public class SearchMoviesServiceImpl implements SearchMoviesService {

    private static final Logger LOG = LoggerFactory.getLogger(SearchMoviesServiceImpl.class);

    @Reference
    private transient TMDBConfigService config;


    @Override
    public TMDBResponseBean callTMDB (String query) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        TMDBResponseBean formattedResult = null;

        if(config != null && config.apiKeyTMDB() != null && !config.apiKeyTMDB().isEmpty()) {
            String theUrl = TMDB_API_CALL_URL_PATH + query;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(theUrl))
                                             .GET()
                                             .header(TMDB_AUTHORIZATION, TMDB_BEARER + config.apiKeyTMDB())
                                             .build();
            LOG.info("SearchMoviesServiceImpl - calling TMDB API for the query: " + query);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null && response.statusCode() == 200 && response.body() != null) {
                try {
                    LOG.info("SearchMoviesServiceImpl - formatting TMDB API response to the TMDBResponseBean class");
                    formattedResult = objectMapper.readValue(response.body(), TMDBResponseBean.class);
                    LOG.info("SearchMoviesServiceImpl - formatting TMDB API response to the TMDBResponseBean class successful");
                } catch (JsonProcessingException e) {
                    LOG.error("SearchMoviesServiceImpl - formatting TMDB API response to the TMDBResponseBean class did not succeed");
                    throw new RuntimeException(e);
                }
            } else {
                LOG.error("SearchMoviesServiceImpl - calling TMDB API did not succeed for query:" + query);
            }
        }

        return formattedResult;
    }

}
