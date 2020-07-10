package io.github.ncc0706.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "github")
public class GithubProperties {

    private String api = "https://api.github.com/repos/";

    private String jsdelivr = "https://cdn.jsdelivr.net/gh/";

    // Github 用户名
    private String username;

    // 仓库名称
    private String repos;

    private String token;

    private String name = "github api";

    private String email = "github.api@gmail.com";

    private String message = "first commit";

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getJsdelivr() {
        return jsdelivr;
    }

    public void setJsdelivr(String jsdelivr) {
        this.jsdelivr = jsdelivr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
