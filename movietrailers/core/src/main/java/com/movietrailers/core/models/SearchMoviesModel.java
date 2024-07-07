package com.movietrailers.core.models;

import com.movietrailers.core.beans.*;
import com.movietrailers.core.services.SearchTMDBMoviesService;
import com.movietrailers.core.services.SearchYoutubeTrailerService;
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
import java.util.ArrayList;
import java.util.List;

import static com.movietrailers.core.constants.MovieTrailersConstants.*;

/**
 * Model for defining the Search Movies Component functionality
 */
@Model(adaptables = SlingHttpServletRequest.class,
    resourceType = SEARCH_MOVIE_COMPONENT_PATH,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchMoviesModel {

    @ValueMapValue(name = "title")
    private String title;
    @ValueMapValue(name = "description")
    private String description;
    @ValueMapValue(name = "errorMessage")
    private String errorMessage;
    @ValueMapValue(name = "resultsIntroduction")
    private String resultsIntroduction;
    @ValueMapValue(name = "resultsFieldsDescription")
    private String resultsFieldsDescription;
    @ValueMapValue(name = "otherMoviesTitle")
    private String otherMoviesTitle;
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
    private SearchTMDBMoviesService moviesService;
    @OSGiService
    private SearchYoutubeTrailerService youtubeService;
    @Self
    private SlingHttpServletRequest request;

    private String mostRelevantMovieTitle = StringUtils.EMPTY;
    private String query = StringUtils.EMPTY;
    private boolean emptyResults = true;
    private MostRelevantMovieBean mostRelevantMovie;
    private MostRelevantMovieBean.Builder mostRelevantMovieBuilder = new MostRelevantMovieBean.Builder();
    private OtherMoviesBean otherMoviesToDisplay;
    private OtherMoviesBean.Builder otherMoviesBuilder = new OtherMoviesBean.Builder();

    @PostConstruct
    protected void init() throws IOException, InterruptedException {
        query = getObtainQueryParameterFromPage();
        if(!query.equals(StringUtils.EMPTY)) {
            emptyResults = false;
        }
        if(!query.equals(StringUtils.EMPTY)) {
            query = formatTextForURICreation(query);
            TMDBResponseBean callResult = moviesService.callTMDB(query);
            if(callResult != null) {
                setMostRelevantMovieProperties(callResult);
                obtainMostRelevantYoutubeIds();
                mostRelevantMovie = new MostRelevantMovieBean(mostRelevantMovieBuilder);
                setOtherMoviesProperties(callResult);
            } else {
                emptyResults = true;
            }

        }
    }

    private String getObtainQueryParameterFromPage() {
        String query = StringUtils.EMPTY;
        if (request != null) {
            RequestParameter parameter = request.getRequestParameter(QUERY);
            if (parameter != null && parameter.getString() != StringUtils.EMPTY) {
                query = parameter.getString();
            }
        }
        return query;
    }

    private String formatTextForURICreation(String text) {
        String result = text;
        if (text.contains(SPACE)) {
            result = text.replaceAll(SPACE, PLUS);
        }
        return result;
    }

    private void setMostRelevantMovieProperties(TMDBResponseBean callResult) {
        if(callResult != null && callResult.getResults() != null && callResult.getResults().get(MOST_RELEVANT_RESULT_INDEX) != null) {
            TMDBMovieDetailsBean mostRelevantMovie = callResult.getResults().get(MOST_RELEVANT_RESULT_INDEX);
            mostRelevantMovieTitle = mostRelevantMovie.getTitle();
            mostRelevantMovieBuilder.withTitle(mostRelevantMovie.getTitle());
            mostRelevantMovieBuilder.withLanguage(mostRelevantMovie.getOriginal_language());
            mostRelevantMovieBuilder.withOverview(mostRelevantMovie.getOverview());
            mostRelevantMovieBuilder.withReleaseDate(mostRelevantMovie.getRelease_date());
            mostRelevantMovieBuilder.withRating(mostRelevantMovie.getVote_average());
            mostRelevantMovieBuilder.withRatingCount(mostRelevantMovie.getVote_count());
        }
    }

    private void obtainMostRelevantYoutubeIds() throws IOException, InterruptedException {
        if(!mostRelevantMovieTitle.equals(StringUtils.EMPTY)) {
            String formattedMovieTitle = formatTextForURICreation(mostRelevantMovieTitle);
            YoutubeResponseBean youtubeCallResult = youtubeService.callYoutubeSearchForVideoId(formattedMovieTitle + TRAILER_SEARCH_TERM, MAXIMUM_NUMBER_OF_TRAILERS);
            if(youtubeCallResult != null) {
                List<String> videoIds = new ArrayList<>();
                for (int i = 0 ; i < MAXIMUM_NUMBER_OF_TRAILERS ; i++) {
                    String id = obtainVideoId(youtubeCallResult, i);
                    if (!id.isEmpty()) {
                        videoIds.add(id);
                    }
                }
                if (!videoIds.isEmpty()) {
                    mostRelevantMovieBuilder.withVideoIds(videoIds);
                }
            }
        } else {
            emptyResults = true;
        }
    }

    private String obtainVideoId(YoutubeResponseBean youtubeCallResult, int index) {
        if (youtubeCallResult.getItems() != null & youtubeCallResult.getItems().get(index) != null && youtubeCallResult.getItems().get(index).getId()!= null && youtubeCallResult.getItems().get(index).getId().getVideoId() != null) {
            return youtubeCallResult.getItems().get(index).getId().getVideoId();
        }
        return StringUtils.EMPTY;
    }

    private void setOtherMoviesProperties(TMDBResponseBean callResult) {
        if(callResult != null && callResult.getResults() != null) {
            List<OtherMoviePropertiesBean> otherMovies = new ArrayList<>();
            int i = 1;
            while (i <= 10 && callResult.getResults().size() > i) {
                if (callResult.getResults().get(i) != null) {
                    otherMovies.add(obtainMovieProperties(callResult.getResults().get(i)));
                }
                i++;
            }
            otherMoviesBuilder.withOtherMovies(otherMovies);
            otherMoviesToDisplay = new OtherMoviesBean(otherMoviesBuilder);
        }
     }

    private OtherMoviePropertiesBean obtainMovieProperties(TMDBMovieDetailsBean movie) {
        OtherMoviePropertiesBean.Builder builder = new OtherMoviePropertiesBean.Builder();
        builder.withTitle(movie.getTitle());
        builder.withOverview(movie.getOverview());
        builder.withReleaseDate(movie.getRelease_date());
        builder.withRating(movie.getVote_average());
        return new OtherMoviePropertiesBean(builder);
    }

    public MostRelevantMovieBean getMostRelevantMovie() {
        return mostRelevantMovie;
    }

    public OtherMoviesBean getOtherMoviesToDisplay() {
        return otherMoviesToDisplay;
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

    public String getResultsFieldsDescription() {
        return resultsFieldsDescription;
    }

    public String getOtherMoviesTitle() {
        return otherMoviesTitle;
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
