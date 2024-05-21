package com.system.ms.schoolMs.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="students")
public class Student    {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "address", nullable = false )
    private String address;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "poneNumber", nullable = false )
    private Integer phoneNumber;
}
