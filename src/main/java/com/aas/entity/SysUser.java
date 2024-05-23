package com.aas.entity;



import lombok.Data;


@Data
public class SysUser {

    private String userId;
    private String email;
    private String userName;
    private String password;
    private String phone;
    private Integer status;

}
