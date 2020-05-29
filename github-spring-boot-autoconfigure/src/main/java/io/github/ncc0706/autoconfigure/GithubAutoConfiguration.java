package io.github.ncc0706.autoconfigure;

import io.github.ncc0706.autoconfigure.service.GithubService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(GithubProperties.class)
public class GithubAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GithubService githubService() {
        return new GithubService();
    }

}
