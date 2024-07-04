package com.movietrailers.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component(service = SearchMoviesService.class)
public class SearchMoviesServiceImpl implements SearchMoviesService {


    @Override
    public TMDBResponseBean callTMDB (String apiKey, String query) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        TMDBResponseBean formattedResult = null;

        String theUrl = "https://api.themoviedb.org/3/search/movie?include_adult=false&query=" + query;
        HttpClient client = HttpClient.newHttpClient();

        // TODO pass API key from an OSGI config
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(theUrl))
            .GET()
            .header("Authorization", "Bearer " + apiKey)
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200 && response.body() != null) {
            try {
                formattedResult = objectMapper.readValue(response.body(), TMDBResponseBean.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return formattedResult;
    }

}
