package com.movietrailers.core.service.impl;

import com.movietrailers.core.config.YoutubeConfigServiceConfiguration;
import com.movietrailers.core.service.YoutubeConfigService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

/**
 * Class for obtaining the Youtube OSGI configurations
 */
@Component(service = YoutubeConfigService.class, immediate = true, configurationPid = "com.movietrailers.core.service.YoutubeConfigService")
@Designate(ocd = YoutubeConfigServiceConfiguration.class)
public class YoutubeConfigServiceImpl implements YoutubeConfigService{

    private String apiKeyYoutube;

    @Activate
    @Modified
    protected void activate(YoutubeConfigServiceConfiguration config) {
        apiKeyYoutube = config.apiKeyYoutube();
    }

    @Override
    public String apiKeyYoutube() {
        return apiKeyYoutube;
    }

}
