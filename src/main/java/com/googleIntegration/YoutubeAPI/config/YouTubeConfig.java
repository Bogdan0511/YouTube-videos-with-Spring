package com.googleIntegration.YoutubeAPI.config;

import com.googleIntegration.YoutubeAPI.youtube.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpExchangeAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class YouTubeConfig {

    static String YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3";

    @Bean
    WebClient webClient(OAuth2AuthorizedClientManager clientManager) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = //
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
        oauth2.setDefaultClientRegistrationId("google");

        return WebClient.builder() //
                .baseUrl(YOUTUBE_V3_API) //
                .apply(oauth2.oauth2Configuration()) //
                .build();
    }

    @Bean
    YouTube client(WebClient webclient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor((WebClientAdapter.create(webclient))).build();
        return factory.createClient(YouTube.class);
    }

}