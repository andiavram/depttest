package com.movietrailers.core.models;

import com.day.cq.wcm.api.Page;
import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

@Model(adaptables = SlingHttpServletRequest.class,
    resourceType = "movietrailers/components/searchmovies",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchMoviesModel {

//    private RequestCache cache;

    @ValueMapValue(name = "title")
    private String title;
    @ValueMapValue(name = "description")
    private String description;
    @ValueMapValue(name = "errorMessage")
    private String errorMessage;
    @ValueMapValue(name = "TMDBApiKey")
    private String TMDBApiKey;
    @ValueMapValue(name = "youtubeApiKey")
    private String youtubeApiKey;

    @OSGiService
    private SearchMoviesService moviesService;

    @OSGiService
    private SearchYoutubeTrailerService youtubeService;

    private String message;

    private String movieTitle = StringUtils.EMPTY;

    private String youtubeVideoId;

    private String query = StringUtils.EMPTY;

    private boolean emptyResults = true;


    @Self
    private SlingHttpServletRequest request;

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        message = "sorry something went wrong and we cannot retrieve the movie information";

        query = getObtainQueryParameterFromPage();
        if(!query.equals(StringUtils.EMPTY)) {
            emptyResults = false;
        }


        if(!query.equals(StringUtils.EMPTY)) {
            query = formatTextForURICreation(query);
            TMDBResponseBean callResult = moviesService.callTMDB(TMDBApiKey, query);
            if(callResult != null) {
                movieTitle = callResult.getResults().get(0).getTitle();

                if(!movieTitle.equals(StringUtils.EMPTY)) {
                    movieTitle = formatTextForURICreation(movieTitle);
                    YoutubeResponseBean youtubeCallResult = youtubeService.callYoutubeSearchForVideoId(youtubeApiKey, movieTitle + "+trailer");
                    if(youtubeCallResult != null) {
                        if (youtubeCallResult.getItems() != null & youtubeCallResult.getItems().get(0) != null && youtubeCallResult.getItems().get(0).getId()!= null && youtubeCallResult.getItems().get(0).getId().getVideoId() != null) {
                            youtubeVideoId = youtubeCallResult.getItems().get(0).getId().getVideoId();
                        } else {
                            emptyResults = true;
                        }

                    } else {
                        emptyResults = true;
                    }
                }

            } else {
                emptyResults = true;
            }

        }

//        youtubeVideoId = "VuCEYInNNKg";

        int x = 0;
    }

    private String formatTextForURICreation(String text) {
        String result = text;
        if (text.contains(" ")) {
            result = text.replaceAll(" ", "+");
        }
        return result;
    }

    private String getObtainQueryParameterFromPage() {
        String query = StringUtils.EMPTY;
        if (request != null) {
            RequestParameter parameter = request.getRequestParameter("query");
            if (parameter != null && parameter.getString() != StringUtils.EMPTY) {
                query = parameter.getString();
            }
        }
        return query;
    }

    public String getMessage() {
        return message;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public boolean isEmptyResults() {
        return emptyResults;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
