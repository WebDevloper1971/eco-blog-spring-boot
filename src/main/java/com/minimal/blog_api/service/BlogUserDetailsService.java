package com.minimal.blog_api.service;



import com.minimal.blog_api.repository.BlogUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private BlogUserRepo blogUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return blogUserRepo.findByUsername(username).orElseThrow();
    }
}
