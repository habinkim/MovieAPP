package com.habin.MovieAPP.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
    public APIResult apiTest(@RequestBody Map<String, Object> params) throws JsonProcessingException {

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        RestTemplate restTemplate = new RestTemplateBuilder()
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
        .setConnectTimeout(Duration.ofMillis(5000))
        .setReadTimeout(Duration.ofMillis(5000))
        .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8), mappingJackson2HttpMessageConverter)
        .build();

        String commonUrl = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";

        URI uri = UriComponentsBuilder.fromHttpUrl(commonUrl)
                            .queryParam("ServiceKey", "2XG52324VM21X23Q10WP")
                            .queryParam("listCount", "500")
                            .queryParam("startCount", "1500")
                            .queryParam("ratedYn", "y")
                            .queryParam("detail", "N")
                            .queryParam("type", "극영화")
                            .queryParam("collection", "kmdb_new2")
                            .build().encode(StandardCharsets.UTF_8).toUri();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
        ObjectMapper mapper = new ObjectMapper();

        APIResult res = mapper.readValue(result.getBody(), new TypeReference<APIResult>() {});

        return res;

    }
    
}
