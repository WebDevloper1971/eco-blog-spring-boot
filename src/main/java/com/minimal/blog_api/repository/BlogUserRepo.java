package com.minimal.blog_api.repository;

import com.minimal.blog_api.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUserRepo extends JpaRepository<BlogUser,String> {
    Optional<BlogUser> findByUsername(String username);
    Optional<BlogUser> findByEmail(String email);
}
