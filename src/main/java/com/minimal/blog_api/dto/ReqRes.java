package com.minimal.blog_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.minimal.blog_api.models.BlogPost;
import com.minimal.blog_api.models.BlogUser;
import jakarta.servlet.http.Cookie;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;

    private String id;

    private String token;
    private String refreshToken;
    private String expirationTime;


}
