package com.movietrailers.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * OSGI configuration for setting up the Youtube API keys
 */
@ObjectClassDefinition(name = "Youtube OSGI Configuration", description = "Sets the Youtube API configurations necessary to perform the API calls")
public @interface YoutubeConfigServiceConfiguration {

    @AttributeDefinition(
        name = "Youtube API Key",
        type = AttributeType.STRING
    )
    String apiKeyYoutube() default "";
}
