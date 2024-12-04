package com.minimal.blog_api.service;

import com.minimal.blog_api.config.JwtAuthFilter;
import com.minimal.blog_api.dto.PostReqRes;
import com.minimal.blog_api.dto.ReqRes;
import com.minimal.blog_api.models.BlogPost;
import com.minimal.blog_api.models.BlogUser;
import com.minimal.blog_api.repository.BlogPostRepo;
import com.minimal.blog_api.repository.BlogUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class BlogPostService  {

    @Autowired
    private BlogPostRepo blogPostRepo;

    @Autowired
    private BlogUserRepo blogUserRepo;

    @Autowired
    JwtAuthFilter jwtAuthFilter;


    public PostReqRes savePost(PostReqRes req) {
        System.out.println("In post add");
        PostReqRes res = new PostReqRes();
        String username = jwtAuthFilter.getLoggedInUserEmail();
        try {
            Optional<BlogUser> optionalBlogUser = blogUserRepo.findByUsername(username);
            if(optionalBlogUser.isPresent()){
                BlogUser postUser = optionalBlogUser.get();
                if(postUser.getPostCount() < 2){

                    BlogPost post = new BlogPost();
                    post.setTitle(req.getTitle());
                    post.setCategory(req.getCategory());
                    post.setSlug(req.getTitle().toLowerCase().replace(" ","-"));
                    post.setImg_url(req.getImg_url());
                    post.setContent(req.getContent());
                    post.setUsername(postUser.getUsername());
                    post.setDescription(req.getDescription());
                    post.setUserId(postUser.getId());
                    post.setCreatedDate(new Date());

                    if(blogPostRepo.findByTitle(post.getTitle()).isPresent()){

                        res.setStatusCode(400);
                        res.setMessage("Post Title Already Exist, Please Select Unique Title");
                        return res;
                    }

                    postUser.setPostCount(postUser.getPostCount() + 1);
                    blogUserRepo.save(postUser);
                    blogPostRepo.save(post);

                    res.setStatusCode(200);
                    res.setMessage("Post saved successfully");
                }else{
                    res.setStatusCode(400);
                    res.setMessage("Post Limit Reached");
                    return res;

                }

            }else {
                res.setStatusCode(400);
                res.setMessage("No User Found, Please Login Again");
                return res;
            }
        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
            return res;
        }
        return res;
    }

    public PostReqRes deletePost(String id) {
        PostReqRes res = new PostReqRes();
       try {
           if(blogPostRepo.findById(id).isPresent()){
               blogPostRepo.deleteById(id);

               res.setStatusCode(200);
               res.setMessage("post deleted");
           }else{
               res.setStatusCode(400);
               res.setMessage("no post found with this id");
               return res;
           }
       }catch (Exception e){
           res.setStatusCode(500);
           res.setMessage(e.getMessage());
           return res;
       }
        return res;
    }

    public PostReqRes getAllPost() {
        PostReqRes res = new PostReqRes();
        try {
            res.setBlogPostList(blogPostRepo.findAll());
            res.setStatusCode(200);
            res.setMessage("All Post List");
        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
            return res;
        }
        return res;
    }

    public PostReqRes getPost(String slug) {
        PostReqRes res = new PostReqRes();
        try {
            Optional<BlogPost> optionalPost = blogPostRepo.findBySlug(slug);
            if(optionalPost.isPresent()){
                BlogPost post = optionalPost.get();

                Optional<BlogUser> optionalUser = blogUserRepo.findById(post.getUserId());
                if(optionalUser.isPresent()){
                    BlogUser user = optionalUser.get();
                    res.setUsername(user.getUsername());
                }
                res.setBlogPost(post);
                res.setStatusCode(200);
                res.setMessage("Blogpost successfully found !");
            }else{
                res.setStatusCode(400);
                res.setMessage("No post found");
                return res;
            }
        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
            return res;
        }
        return res;
    }

    public PostReqRes getPostId(PostReqRes req) {
        PostReqRes res = new PostReqRes();
       try {
           Optional<BlogPost> optionalPost = blogPostRepo.findById(req.getId());
           if(optionalPost.isPresent()){
               BlogPost post = optionalPost.get();

               Optional<BlogUser> optionalUser = blogUserRepo.findById(post.getUserId());
               if(!post.getUserId().equals(req.getUserId())){

                   res.setStatusCode(400);
                   res.setMessage("You are the post owner");
                   return  res;
               }
               if(optionalUser.isPresent()){
                   BlogUser user = optionalUser.get();
                   res.setUsername(user.getUsername());
               }
               res.setBlogPost(post);
               res.setStatusCode(200);
               res.setMessage("Blogpost successfully found !");
           }else{
               res.setStatusCode(400);
               res.setMessage("No post found with this id");
               return res;
           }
       }catch (Exception e){
           res.setStatusCode(500);
           res.setMessage(e.getMessage());
           return res;
       }
        return res;
    }
    
    public PostReqRes updatePost(PostReqRes req) {
        PostReqRes res = new PostReqRes();

        try{
            String email = jwtAuthFilter.getLoggedInUserEmail();

                Optional<BlogUser> optionalBlogUser = blogUserRepo.findByUsername(email);
                if(optionalBlogUser.isPresent()) {
                    BlogUser postUser = optionalBlogUser.get();
                    if(postUser.getUpdateCount() < 2){
                        postUser.setUpdateCount(postUser.getUpdateCount() + 1);
                        Optional<BlogPost> optionalPost = blogPostRepo.findById(req.getId());
                        if(optionalPost.isPresent()){

                            BlogPost postToBeUpdated = optionalPost.get();
                            if(postUser.getId().equals(postToBeUpdated.getUserId())){
                                if(!req.getTitle().equals(postToBeUpdated.getTitle())){
                                    postToBeUpdated.setTitle(req.getTitle());
                                }

                                if(!req.getImg_url().equals(postToBeUpdated.getImg_url())){
                                    postToBeUpdated.setImg_url(req.getImg_url());
                                }


                                if(!req.getCategory().equals(postToBeUpdated.getCategory())){
                                    postToBeUpdated.setCategory(req.getCategory());
                                }

                                if(!req.getDescription().equals(postToBeUpdated.getDescription())){
                                    postToBeUpdated.setDescription(req.getDescription());
                                }

                                if(!req.getContent().equals(postToBeUpdated.getContent())){
                                    postToBeUpdated.setContent(req.getContent());
                                }

                                blogUserRepo.save(postUser);
                                postToBeUpdated.setUpdatedDate(new Date());
                                BlogPost updatedBlogpost = blogPostRepo.save(postToBeUpdated);


                                res.setStatusCode(200);
                                res.setBlogPost(updatedBlogpost);
                                res.setMessage("Post Updated successfully");
                            }else{
                                res.setStatusCode(400);
                                res.setMessage("Post User Id does not match");
                                return res;
                            }

                        }else{
                            res.setStatusCode(400);
                            res.setMessage("No post is found with given Id");
                            return res;
                        }
                    }else {
                        res.setStatusCode(400);
                        res.setMessage("Update limit reached, Contact Developer For Further Process");
                        return res;
                    }


                }else{
                    res.setStatusCode(400);
                    res.setMessage("No user found, login again");
                    return res;
                }

        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
            return res;
        }

        return res;
    }
}
