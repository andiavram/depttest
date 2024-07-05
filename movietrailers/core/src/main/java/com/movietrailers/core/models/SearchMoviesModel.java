package com.movietrailers.core.models;

import com.movietrailers.core.beans.TMDBMovieDetailsBean;
import com.movietrailers.core.beans.TMDBResponseBean;
import com.movietrailers.core.beans.YoutubeResponseBean;
import com.movietrailers.core.service.SearchMoviesService;
import com.movietrailers.core.service.SearchYoutubeTrailerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.io.IOException;

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
    @ValueMapValue(name = "resultsIntroduction")
    private String resultsIntroduction;
    @ValueMapValue(name = "resultsFieldsDescription")
    private String resultsFieldsDescription;
    @ValueMapValue(name = "movieTitleLabel")
    private String movieTitleLabel;
    @ValueMapValue(name = "movieLanguageLabel")
    private String movieLanguageLabel;
    @ValueMapValue(name = "movieOverviewLabel")
    private String movieOverviewLabel;
    @ValueMapValue(name = "movieReleaseDateLabel")
    private String movieReleaseDateLabel;
    @ValueMapValue(name = "movieRatingLabel")
    private String movieRatingLabel;
    @ValueMapValue(name = "movieRatingCountLabel")
    private String movieRatingCountLabel;

    @OSGiService
    private SearchMoviesService moviesService;

    @OSGiService
    private SearchYoutubeTrailerService youtubeService;

    private String movieTitle = StringUtils.EMPTY;
    private String movieLanguage = StringUtils.EMPTY;
    private String movieOverview = StringUtils.EMPTY;
    private String movieReleaseDate = StringUtils.EMPTY;
    private String movieRating = StringUtils.EMPTY;
    private String movieRatingCount = StringUtils.EMPTY;

    private String youtubeVideoId1 = StringUtils.EMPTY;
    private String youtubeVideoId2 = StringUtils.EMPTY;
    private String youtubeVideoId3 = StringUtils.EMPTY;

    private String query = StringUtils.EMPTY;

    private boolean emptyResults = true;


    @Self
    private SlingHttpServletRequest request;

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        query = getObtainQueryParameterFromPage();
        if(!query.equals(StringUtils.EMPTY)) {
            emptyResults = false;
        }


        if(!query.equals(StringUtils.EMPTY)) {
            query = formatTextForURICreation(query);
            TMDBResponseBean callResult = moviesService.callTMDB(TMDBApiKey, query);
            if(callResult != null) {

                TMDBMovieDetailsBean mostRelevantMovie = callResult.getResults().get(0);
                movieTitle = mostRelevantMovie.getTitle();
                movieLanguage = mostRelevantMovie.getOriginal_language();
                movieOverview = mostRelevantMovie.getOverview();
                movieReleaseDate = mostRelevantMovie.getRelease_date();
                movieRating = mostRelevantMovie.getVote_average();
                movieRatingCount = mostRelevantMovie.getVote_count();

                if(!movieTitle.equals(StringUtils.EMPTY)) {
                    movieTitle = formatTextForURICreation(movieTitle);
                    YoutubeResponseBean youtubeCallResult = youtubeService.callYoutubeSearchForVideoId(youtubeApiKey, movieTitle + "+trailer");
                    if(youtubeCallResult != null) {
                        if (youtubeCallResult.getItems() != null & youtubeCallResult.getItems().get(0) != null && youtubeCallResult.getItems().get(0).getId()!= null && youtubeCallResult.getItems().get(0).getId().getVideoId() != null) {
                            youtubeVideoId1 = youtubeCallResult.getItems().get(0).getId().getVideoId();
                        }
                        if (youtubeCallResult.getItems() != null & youtubeCallResult.getItems().get(1) != null && youtubeCallResult.getItems().get(1).getId()!= null && youtubeCallResult.getItems().get(1).getId().getVideoId() != null) {
                            youtubeVideoId2 = youtubeCallResult.getItems().get(1).getId().getVideoId();
                        }
                        if (youtubeCallResult.getItems() != null & youtubeCallResult.getItems().get(2) != null && youtubeCallResult.getItems().get(2).getId()!= null && youtubeCallResult.getItems().get(2).getId().getVideoId() != null) {
                            youtubeVideoId3 = youtubeCallResult.getItems().get(2).getId().getVideoId();
                        }
                    }
                } else {
                    emptyResults = true;
                }
            } else {
                emptyResults = true;
            }

        }

//        youtubeVideoId1 = "VuCEYInNNKg";

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

    public String getYoutubeVideoId1() {
        return youtubeVideoId1;
    }

    public String getYoutubeVideoId2() {
        return youtubeVideoId2;
    }

    public String getYoutubeVideoId3() {
        return youtubeVideoId3;
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

    public String getResultsIntroduction() {
        return resultsIntroduction;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMovieRatingCount() {
        return movieRatingCount;
    }

    public String getResultsFieldsDescription() {
        return resultsFieldsDescription;
    }

    public String getMovieTitleLabel() {
        return movieTitleLabel;
    }

    public String getMovieLanguageLabel() {
        return movieLanguageLabel;
    }

    public String getMovieOverviewLabel() {
        return movieOverviewLabel;
    }

    public String getMovieReleaseDateLabel() {
        return movieReleaseDateLabel;
    }

    public String getMovieRatingLabel() {
        return movieRatingLabel;
    }

    public String getMovieRatingCountLabel() {
        return movieRatingCountLabel;
    }
}
