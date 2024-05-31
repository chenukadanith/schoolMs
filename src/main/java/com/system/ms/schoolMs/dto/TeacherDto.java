package com.system.ms.schoolMs.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
}
