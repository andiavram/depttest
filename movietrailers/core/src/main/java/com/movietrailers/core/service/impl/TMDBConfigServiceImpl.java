package com.movietrailers.core.service.impl;

import com.movietrailers.core.config.TMDBConfigServiceConfiguration;
import com.movietrailers.core.service.TMDBConfigService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

/**
 * Class for obtaining the TMDB OSGI configurations
 */
@Component(service = TMDBConfigService.class, immediate = true, configurationPid = "com.movietrailers.core.service.TMDBConfigService")
@Designate(ocd = TMDBConfigServiceConfiguration.class)
public class TMDBConfigServiceImpl implements TMDBConfigService{

    private String apiKeyTMDB;

    @Activate
    @Modified
    protected void activate(TMDBConfigServiceConfiguration config) {
        apiKeyTMDB = config.apiKeyTMDB();
    }

    @Override
    public String apiKeyTMDB() {
        return apiKeyTMDB;
    }

}
