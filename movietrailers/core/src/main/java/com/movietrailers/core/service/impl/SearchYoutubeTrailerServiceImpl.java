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

@Component(service = SearchYoutubeTrailerService.class)
public class SearchYoutubeTrailerServiceImpl implements SearchYoutubeTrailerService {


    @Override
    public YoutubeResponseBean callYoutubeSearchForVideoId (String apiKey, String query) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        YoutubeResponseBean formattedResult = null;

        String theUrl = "https://www.googleapis.com/youtube/v3/search?key=" + apiKey + "&q=" + query;
        HttpClient client = HttpClient.newHttpClient();

//        String encoded = java.net.URLEncoder.encode("https://www.googleapis.com/youtube/v3/search?key=" + apiKey + "&q=" + query, "UTF-8").replace("+", "%20");


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
