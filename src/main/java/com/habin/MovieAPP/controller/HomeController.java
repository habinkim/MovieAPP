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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public HashMap<String, Object> apiTest(@RequestBody Map<String, Object> params) throws JsonProcessingException {

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
        .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8), 
                                    new MappingJackson2HttpMessageConverter()
        )
        .build();

        String url = new StringBuilder()
                        .append("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp")
                        .append("?")
                        .append("key=2XG52324VM21X23Q10WP")
                        .append("&")
                        .append("listCount=500")
                        .append("&")
                        .append("startCount=1500")
                        .append("&")
                        .append("ratedYn=y")
                        .append("&")
                        .append("detail=N")
                        .append("&")
                        .append("collection=kmdb_new2")
                        .toString();

        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
        result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

        //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
        ObjectMapper mapper = new ObjectMapper();
        jsonInString = mapper.writeValueAsString(resultMap.getBody());

        return result;

    }
    
}
