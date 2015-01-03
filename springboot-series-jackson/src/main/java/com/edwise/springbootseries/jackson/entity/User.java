package com.edwise.springbootseries.jackson.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User {

    private long id;
    private String name;
    private LocalDateTime birthDateTime;

}
