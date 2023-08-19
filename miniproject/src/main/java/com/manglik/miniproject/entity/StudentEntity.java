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
@Entity(name = "student")
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public class StudentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "student_id")
        private int studentId;
        @Column(name = "student_name")
        private String studentName;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "stu_id"),
            inverseJoinColumns = @JoinColumn(name = "cor_id"))
    @JsonBackReference
    private List<CourseEntity> courseEntities;


}

