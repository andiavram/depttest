package com.movietrailers.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "TMDB - Osgi Config", description = "Configuration used for all properties needed for connecting to TMDS API")
public @interface ConfigTMDB {

    @AttributeDefinition(name = "TMDB Key")
    String key();

}
