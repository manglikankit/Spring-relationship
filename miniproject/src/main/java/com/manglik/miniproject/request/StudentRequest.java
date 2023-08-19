package com.manglik.miniproject.request;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentRequest {
    private int studentId;
    private String studentName;
    private List<CourseRequest> courses;

}
