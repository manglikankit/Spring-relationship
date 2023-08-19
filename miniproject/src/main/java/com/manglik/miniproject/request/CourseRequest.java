package com.manglik.miniproject.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseRequest
{
    private int courseId;
    private String courseTitle;
    private int authorId;

    private List<StudentRequest> students;


}
