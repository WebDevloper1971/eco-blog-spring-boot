package com.minimal.blog_api.controllers;

import com.minimal.blog_api.dto.PostReqRes;
import com.minimal.blog_api.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;


    @GetMapping("/all-posts")
    public ResponseEntity<PostReqRes> allPosts(){
        return ResponseEntity.ok(blogPostService.getAllPost());
    }

    @GetMapping("/all-categories")
    public List<String> getAllCategories(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Ecology");
        categoryList.add("Pollution");
        categoryList.add("Climate Control");
        categoryList.add("Climate Change");
        categoryList.add("Global Warming");
        categoryList.add("Solar Energy");
        categoryList.add("Water Energy");
        categoryList.add("Wind Energy");
        categoryList.add("Agriculture");
        categoryList.add("Permaculture");
        categoryList.add("Rain Water Harvesting");
        categoryList.add("Garbage Management");
        categoryList.add("CO2 Management");
        return categoryList;
    }


    @GetMapping("/post/{slug}")
    public ResponseEntity<PostReqRes> getPost(@PathVariable("slug") String slug){
        return ResponseEntity.ok(blogPostService.getPost(slug));
    }

    @PostMapping("/user/add-post")
    @ExceptionHandler
    public ResponseEntity<PostReqRes> addPost(@RequestBody PostReqRes req){
        return ResponseEntity.ok(blogPostService.savePost(req));
    }

    @PutMapping("/user/update-post")
    public ResponseEntity<PostReqRes> updatePost(@RequestBody PostReqRes req){
        return ResponseEntity.ok(blogPostService.updatePost(req));
    }

    @PostMapping("/user/post")
    public ResponseEntity<PostReqRes> getPostById(@RequestBody PostReqRes req){
        return ResponseEntity.ok(blogPostService.getPostId(req));
    }

    @DeleteMapping("/admin/delete-post/{id}")
    public ResponseEntity<PostReqRes> deletePost(@PathVariable("id") String id){
        return ResponseEntity.ok(blogPostService.deletePost(id));
    }
}
