package com.system.ms.schoolMs.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
}
