package com.manglik.miniproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "courses")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "course_title")
    private String courseTitle;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "auther_id")
    @JsonBackReference
    private AutherEntity autherEntity;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "cor_id"),
            inverseJoinColumns = @JoinColumn(name = "stu_id"))
    @JsonBackReference
    private List<StudentEntity> studentEntities;

}
