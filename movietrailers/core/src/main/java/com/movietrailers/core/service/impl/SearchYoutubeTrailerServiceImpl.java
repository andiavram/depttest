package com.movietrailers.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import org.osgi.service.component.annotations.Component;

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


    @Override
    public YoutubeResponseBean callYoutubeSearchForVideoId (String apiKey, String query) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        YoutubeResponseBean formattedResult = null;

        String theUrl = YOUTUBE_SEARCH_API_CALL_URL_PATH + apiKey + YOUTUBE_SEARCH_API_QUERY_MARKER + query;
        HttpClient client = HttpClient.newHttpClient();
        // TODO pass API key from an OSGI config
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

        return formattedResult;
    }

}
