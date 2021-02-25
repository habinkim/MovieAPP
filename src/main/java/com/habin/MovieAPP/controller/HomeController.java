package com.habin.MovieAPP.controller;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Configuration
@RequestMapping(value = "/home")
public class HomeController {

    @PostMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@RequestBody Map<String, Object> params) {

        String param = params.get("param").toString();

        return param;
        
    }

    @PostMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String apiTest(@RequestBody Map<String, Object> params) throws JsonProcessingException {

        // HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // factory.setConnectTimeout(5000);
        // factory.setReadTimeout(5000);
        // RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        RestTemplate restTemplate = new RestTemplateBuilder()
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
        .setConnectTimeout(Duration.ofMillis(5000))
        .setReadTimeout(Duration.ofMillis(5000))
        .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
        .build();

        String url = new StringBuilder()
                        .append("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp")
                        .append("?")
                        .append("key=2XG52324VM21X23Q10WP")
                        .append("&")
                        .append("collection=kmdb_new2")
                        .toString();

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<HashMap> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, HashMap.class);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(resultMap.getBody());

        return jsonString;

    }
    
}
