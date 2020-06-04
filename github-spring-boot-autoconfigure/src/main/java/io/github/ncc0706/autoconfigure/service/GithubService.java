package io.github.ncc0706.autoconfigure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ncc0706.autoconfigure.GithubProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GithubService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GithubProperties githubProperties;

    public Map<String, String> upload(MultipartFile file) throws IOException {

        String encode = Base64Utils.encodeToString(file.getBytes());

        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String fileName = uuid + ext;

        String url = githubProperties.getApi() + githubProperties.getUsername() + "/" + githubProperties.getRepos() + "/contents/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "token " + githubProperties.getToken());
        Map<String, Object> params = new HashMap<>();
        params.put("message", githubProperties.getMessage());
        Map<String, String> committer = new HashMap<>();
        committer.put("name", githubProperties.getName());
        committer.put("email", githubProperties.getEmail());
        params.put("committer", committer);
        params.put("content", encode);
        String data = new ObjectMapper().writeValueAsString(params);

        HttpEntity<String> httpEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);

        if (logger.isDebugEnabled()) {
            logger.debug("response: {}", exchange.getBody());
        }

        String result = githubProperties.getJsdelivr() + githubProperties.getUsername() + "/" + githubProperties.getRepos() + "@latest/" + fileName;

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", result);
        resultMap.put("UBB", "[img]" + result + "[/img]");
        resultMap.put("markdown", "![](" + result + ")");
        return resultMap;
    }
}
