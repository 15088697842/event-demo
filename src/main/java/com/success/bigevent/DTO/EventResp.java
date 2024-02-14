package com.success.bigevent.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventResp implements Serializable {

    private String name;

    private String type;
}
