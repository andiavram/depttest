package com.movietrailers.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import com.movietrailers.core.service.YoutubeConfigService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.movietrailers.core.constants.MovieTrailersConstants.*;

/**
 * Class for defining the Youtube API call
 */
@Component(service = SearchYoutubeTrailerService.class)
public class SearchYoutubeTrailerServiceImpl implements SearchYoutubeTrailerService {

    @Reference
    private transient YoutubeConfigService config;

    @Override
    public YoutubeResponseBean callYoutubeSearchForVideoId (String query, int maxResults) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        YoutubeResponseBean formattedResult = null;

        if(config != null && config.apiKeyYoutube() != null && !config.apiKeyYoutube().isEmpty()) {
            String theUrl = YOUTUBE_SEARCH_API_CALL_URL_PATH + config.apiKeyYoutube() + YOUTUBE_SEARCH_API_QUERY_MARKER + query + YOUTUBE_SEARCH_API_MAX_RESULTS_MARKER + maxResults;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(theUrl))
                                             .GET()
                                             .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null && response.statusCode() == 200 && response.body() != null) {
                try {
                    formattedResult = objectMapper.readValue(response.body(), YoutubeResponseBean.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return formattedResult;
    }

}
