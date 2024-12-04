package com.minimal.blog_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.minimal.blog_api.models.BlogUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqRes extends ReqRes{

    private String username;
    private String email;
    private String password;
    private String updatedPassword;
    private String secret;
    private BlogUser blogUser;
    private List<BlogUser> blogUserList;

}
