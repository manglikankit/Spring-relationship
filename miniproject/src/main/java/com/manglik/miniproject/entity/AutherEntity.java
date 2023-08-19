package com.manglik.miniproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
    @Entity(name = "auther")
    @Table
    public class AutherEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "auther_id")
        private int autherId;
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;

    @OneToMany(mappedBy = "autherEntity",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<CourseEntity> courseEntities;


    }

