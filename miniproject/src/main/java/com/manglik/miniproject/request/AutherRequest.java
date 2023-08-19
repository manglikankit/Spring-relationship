package com.manglik.miniproject.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AutherRequest {
    private int autherId;
    private String firstName;
    private String lastName;


}
