package com.github.yevheniivalkovskyi.config;

import com.github.yevheniivalkovskyi.service.MonoService;
import com.github.yevheniivalkovskyi.service.MonoServiceImpl;
import com.github.yevheniivalkovskyi.util.MonoApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MonoApiProperties.class)
@ConditionalOnClass(RestTemplate.class)
@ConditionalOnProperty(prefix = "mono", name = "token")
public class MonoApiAutoConfiguration
{
    private final MonoApiProperties properties;
    private static final String BASE_URL = "https://api.monobank.ua";

    @Bean
    public MonoService monoService()
    {
        return new MonoServiceImpl(restTemplate());
    }

    private RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(BASE_URL).build();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new RestTemplateRequestInterceptor("X-Token", properties.getToken()));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
