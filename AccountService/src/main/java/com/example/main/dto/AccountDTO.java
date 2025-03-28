package com.example.main.dto;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private Long id;

    private String name;

    private String password;

    private String username;

    private Set<String> roles;
}
