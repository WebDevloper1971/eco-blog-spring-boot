package com.minimal.blog_api.service;


import com.minimal.blog_api.dto.ReqRes;
import com.minimal.blog_api.dto.UserReqRes;
import com.minimal.blog_api.models.BlogUser;
import com.minimal.blog_api.repository.BlogUserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BlogUserManagementService {

    @Autowired
    private BlogUserRepo blogUserRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public UserReqRes register(UserReqRes req){
        UserReqRes res = new UserReqRes();
        try{
            BlogUser blogUser = new BlogUser();

            if(blogUserRepo.findByEmail(req.getEmail()).isEmpty()){
                blogUser.setEmail(req.getEmail());
                if(blogUserRepo.findByUsername(req.getUsername()).isEmpty()){
                    blogUser.setUsername(req.getUsername());
                }else{
                    res.setStatusCode(400);
                    res.setMessage("username is already taken");
                    return res;
                }
            }else{
                res.setStatusCode(400);
                res.setMessage("email is already registered");
                return res;
            }
            blogUser.setPassword(passwordEncoder.encode(req.getPassword()));
            blogUser.setSecret(req.getSecret());
            BlogUser savedUser = blogUserRepo.save(blogUser);

            if(!savedUser.getId().isEmpty()){
                res.setBlogUser(savedUser);
                res.setMessage("User saved successfully");
                res.setStatusCode(200);
            }

        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());

        }
        return res;
    }

    public UserReqRes login(UserReqRes req){
        UserReqRes res = new UserReqRes();

        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            var user = blogUserRepo.findByUsername(req.getUsername()).orElseThrow();

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),user);

            res.setStatusCode(200);
            res.setId(user.getId());
            res.setUsername(user.getUsername());
            res.setToken(jwt);
            res.setRefreshToken(refreshToken);
            res.setExpirationTime("20min");
            res.setMessage("Successfully Logged In");


        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }


        return res;
    }

    public UserReqRes refreshToken(UserReqRes req){
        UserReqRes res = new UserReqRes();

        try{
           String email = jwtUtils.extractUsername(req.getToken());
           BlogUser blogUser = blogUserRepo.findByUsername(email).orElseThrow();
           if(jwtUtils.isTokenValid(req.getToken(), blogUser)){
               var jwt = jwtUtils.generateToken(blogUser);

               res.setStatusCode(200);
               res.setToken(jwt);
               res.setRefreshToken(req.getRefreshToken());
               res.setExpirationTime("20min");
               res.setMessage("successfully refreshed token");

           }

           res.setStatusCode(200);
           return res;

        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return res;
    }

    public UserReqRes getAllUsers(){
        UserReqRes res = new UserReqRes();
        try{
            List<BlogUser> allUsers = blogUserRepo.findAll();
            if(!allUsers.isEmpty()){
                res.setBlogUserList(allUsers);
                res.setStatusCode(200);
                res.setMessage("Successful");
            }else{
                res.setStatusCode(400);
                res.setMessage("No users found");
            }
        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }

        return res;
    }

    public UserReqRes getUserById(String id){
        UserReqRes res = new UserReqRes();
        try{
            BlogUser blogUser = blogUserRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
            res.setBlogUser(blogUser);
            res.setStatusCode(200);
            res.setMessage("User with " + id + " found successfully");
        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }

        return res;
    }

    public UserReqRes deleteUser(String id){
        UserReqRes res = new UserReqRes();
        try{
            Optional<BlogUser> bui = blogUserRepo.findById(id);

            if(bui.isPresent()){
                BlogUser blogUser = bui.get();
                blogUserRepo.deleteById(id);
                res.setBlogUser(blogUser);
                res.setStatusCode(200);
                res.setBlogUser(blogUser);
                res.setMessage("User deleted successfully");
            }else{
                res.setStatusCode(404);
                res.setMessage("user not found for deletion");
            }

        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }

        return res;


    }

    public UserReqRes updateUser(UserReqRes req){
        UserReqRes res = new UserReqRes();
        try{
           String email = req.getEmail();
           String password = req.getPassword();
           String updatedPassword = req.getUpdatedPassword();
           String secret = req.getSecret();

            Optional<BlogUser> optionalUser = blogUserRepo.findByUsername(req.getEmail());

           if(optionalUser.isPresent()){
               BlogUser original = optionalUser.get();

               if(passwordEncoder.matches(password,original.getPassword())){

                   if(secret.equals((original.getSecret()))){
                       original.setPassword(passwordEncoder.encode(updatedPassword));
                       blogUserRepo.save(original);
                       res.setStatusCode(200);
                       res.setMessage("Password change successful");
                   }else {
                       res.setStatusCode(400);
                       res.setMessage("secret is incorrect");
                       return res;
                   }

               }else{
                   res.setStatusCode(400);
                   res.setMessage("Password is incorrect");
                   return res;
               }

           }else{
               res.setStatusCode(400);
               res.setMessage("No User Found With Current Id");
               return res;
           }





        }catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }

        return res;


    }

    public UserReqRes loginExpired(){
        UserReqRes res = new UserReqRes();
        res.setStatusCode(400);
        res.setMessage("Login session expired");
        return res;
    }
}
