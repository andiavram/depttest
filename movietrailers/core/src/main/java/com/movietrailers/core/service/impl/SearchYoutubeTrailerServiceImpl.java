package com.movietrailers.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import com.movietrailers.core.service.YoutubeConfigService;
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
 * Class for defining the Youtube API call
 */
@Component(service = SearchYoutubeTrailerService.class)
public class SearchYoutubeTrailerServiceImpl implements SearchYoutubeTrailerService {

    private static final Logger LOG = LoggerFactory.getLogger(SearchYoutubeTrailerServiceImpl.class);

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
            LOG.info("SearchYoutubeTrailerServiceImpl - calling Youtube API for the query: " + query);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null && response.statusCode() == 200 && response.body() != null) {
                try {
                    LOG.info("SearchYoutubeTrailerServiceImpl - formatting Youtube API response to the YoutubeResponseBean class");
                    formattedResult = objectMapper.readValue(response.body(), YoutubeResponseBean.class);
                    LOG.info("SearchYoutubeTrailerServiceImpl - formatting Youtube API response to the YoutubeResponseBean class successful");
                } catch (JsonProcessingException e) {
                    LOG.error("SearchYoutubeTrailerServiceImpl - formatting Youtube API response to the YoutubeResponseBean class did not succeed");
                    throw new RuntimeException(e);
                }
            } else {
                LOG.error("SearchYoutubeTrailerServiceImpl - calling Youtube API did not succeed for query:" + query);
            }
        }

        return formattedResult;
    }

}
