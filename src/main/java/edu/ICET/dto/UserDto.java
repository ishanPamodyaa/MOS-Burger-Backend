package edu.ICET.dto;


import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String userRole;

}
