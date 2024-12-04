package com.minimal.blog_api.controllers;

import com.minimal.blog_api.dto.ReqRes;
import com.minimal.blog_api.dto.UserReqRes;
import com.minimal.blog_api.service.BlogUserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BlogUserController {

    @Autowired
    private BlogUserManagementService blogUserManagementService;


    @PostMapping(value = "/auth/signup")
    public ResponseEntity<UserReqRes> signup(@RequestBody UserReqRes req){
        return ResponseEntity.ok(blogUserManagementService.register(req));
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<UserReqRes> login(@RequestBody UserReqRes req){
        return ResponseEntity.ok(blogUserManagementService.login(req));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserReqRes> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(blogUserManagementService.getUserById(id));
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<UserReqRes> update(@RequestBody UserReqRes req){
        return ResponseEntity.ok(blogUserManagementService.updateUser(req));
    }

    @GetMapping(value = "/admin/get-all-users")
    public ResponseEntity<UserReqRes> getAllUsers(){
        return ResponseEntity.ok(blogUserManagementService.getAllUsers());
    }

    @DeleteMapping(value = "/admin/delete-user/{id}")
    public ResponseEntity<UserReqRes> deleteUser(@PathVariable("id") String id){
        return ResponseEntity.ok(blogUserManagementService.deleteUser(id));
    }

}
