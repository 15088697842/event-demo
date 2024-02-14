package com.success.bigevent.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResp implements Serializable {

    private Integer id;

    private String name;

    private Integer role;

    public UserResp() {
    }

    public UserResp(Integer id, String name, Integer role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
