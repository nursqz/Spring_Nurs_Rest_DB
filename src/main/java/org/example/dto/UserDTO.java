package org.example.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long userId;
    private String userName;
    private String userEmail;
    private String password;

    public UserDTO() {}
}