package com.movietrailers.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * OSGI configuration for setting up the TMDB API keys
 */
@ObjectClassDefinition(name = "TMDB OSGI Configuration", description = "Sets the TMDB API configurations necessary to perform the API calls")
public @interface TMDBConfigServiceConfiguration {

    @AttributeDefinition(
        name = "TMDB API Key",
        type = AttributeType.STRING
    )
    String apiKeyTMDB() default "";
}
