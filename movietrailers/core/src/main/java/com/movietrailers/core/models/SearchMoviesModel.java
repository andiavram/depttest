package com.movietrailers.core.models;

import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
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

    @ValueMapValue(name = "apiKey")
    private String apiKey;

    @OSGiService
    private SearchMoviesService moviesService;

    private String message;

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        message = "sorry something went wrong and we cannot retrieve the movie information";
        TMDBResponseBean callResult = moviesService.callTMDB(apiKey);
        if(callResult != null) {
            message = callResult.getResults().get(0).getTitle();
        }
        int x = 0;

    }

    public String getMessage() {
        return message;
    }

}
