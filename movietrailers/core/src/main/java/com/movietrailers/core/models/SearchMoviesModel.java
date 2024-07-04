package com.movietrailers.core.models;

import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = SlingHttpServletRequest.class,
    resourceType = "movietrailers/components/searchmovies",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchMoviesModel {

//    private RequestCache cache;

    @ValueMapValue(name = "TMDBApiKey")
    private String TMDBApiKey;

    @ValueMapValue(name = "youtubeApiKey")
    private String youtubeApiKey;

    @OSGiService
    private SearchMoviesService moviesService;

    @OSGiService
    private SearchYoutubeTrailerService youtubeService;

    private String message;

    private String youtubeVideoId;

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        message = "sorry something went wrong and we cannot retrieve the movie information";
        TMDBResponseBean callResult = moviesService.callTMDB(TMDBApiKey);
        if(callResult != null) {
            message = callResult.getResults().get(0).getTitle();
        }

        YoutubeResponseBean youtubeCallResult = youtubeService.callYoutubeSearchForVideoId(youtubeApiKey, message + "trailer");
        youtubeVideoId = youtubeCallResult.getItems().get(0).getId().getVideoId();

        int x = 0;

    }

    public String getMessage() {
        return message;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }
}
