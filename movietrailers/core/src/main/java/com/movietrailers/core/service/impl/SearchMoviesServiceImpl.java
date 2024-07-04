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
    public TMDBResponseBean callTMDB () throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        TMDBResponseBean formattedResult = null;

        // TODO pass query from an input field
        String theUrl = "https://api.themoviedb.org/3/search/movie?include_adult=false&query=minions";
        HttpClient client = HttpClient.newHttpClient();

        // TODO pass API key from an OSGI config
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(theUrl))
            .GET()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NzVlZDk2MTNlNGIyY2VlNjk3ZTU0MTdmNjU3NmExNCIsIm5iZiI6MTcxOTk4ODQwMC4zODIyMDUsInN1YiI6IjY2ODRlZmFlNDU5Nzc4OTZkYTFmNzAxMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7ZXayVRAilcyKTLIyBSFU_NUTTCDDdoFybJsA-MRyEY")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // TODO check that the response status is 200
        if (response != null && response.body() != null) {
            try {
                formattedResult = objectMapper.readValue(response.body(), TMDBResponseBean.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return formattedResult;
    }

}
