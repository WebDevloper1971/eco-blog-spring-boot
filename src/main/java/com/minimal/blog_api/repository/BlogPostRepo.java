package com.minimal.blog_api.repository;

import com.minimal.blog_api.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost,String> {
    Optional<BlogPost> findBySlug(String slug);
    Optional<BlogPost> findByTitle(String title);
}
