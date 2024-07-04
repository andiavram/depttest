package com.movietrailers.core.models;

import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = SlingHttpServletRequest.class,
    resourceType = "movietrailers/components/searchmovies",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchMoviesModel {

//    private RequestCache cache;

    @OSGiService
    private SearchMoviesService moviesService;

    private String message;

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        message = "something";
        TMDBResponseBean callResult = moviesService.callTMDB();
        message = callResult.getResults().get(0).getTitle();
        int x = 0;

    }

    public String getMessage() {
        return message;
    }

}
