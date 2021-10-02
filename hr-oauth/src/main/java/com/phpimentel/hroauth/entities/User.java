package com.phpimentel.hroauth.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();
}
