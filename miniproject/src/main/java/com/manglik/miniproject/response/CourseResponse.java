package com.manglik.miniproject.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.manglik.miniproject.entity.StudentEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CourseResponse {
    private int courseId;
    private String courseTitle;
    private int authorId;

    private List<StudentEntity> students;


}
