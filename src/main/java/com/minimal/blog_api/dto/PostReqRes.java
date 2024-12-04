package com.minimal.blog_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.minimal.blog_api.models.BlogPost;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostReqRes extends ReqRes{

    private String username;
    private String title;
    private String category;
    private String description;
    private String img_url;
    private String userId;
    private String content;
    private BlogPost blogPost;
    private List<BlogPost> blogPostList;

}
