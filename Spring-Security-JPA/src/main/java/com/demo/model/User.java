package com.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {

   @Id
    private int id;
    private String username;
    private String password;

    private String roles;

    private boolean active;
}
