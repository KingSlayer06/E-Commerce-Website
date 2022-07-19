package com.KingSlayer.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty
    @Column(name = "lastname")
    private String lastName;

    @NotEmpty
    @Column(name = "email")
    @Email(message = "{errors.invalid_email}")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;
}
