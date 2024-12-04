package com.minimal.blog_api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true,length = 300, nullable = false)
    private String title;

    @Column(length = 50,nullable = false)
    private String category;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(length = 300)
    private String img_url;

    @Column(nullable = false,length = 200)
    private String description;

    @Column(columnDefinition = "TEXT",length = 150000,nullable = false)
    private String content;

    private String userId;

    private String username;

    private Date createdDate;

    private Date updatedDate;


}
