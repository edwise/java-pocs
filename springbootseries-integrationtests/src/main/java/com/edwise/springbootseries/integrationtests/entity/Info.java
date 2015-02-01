package com.edwise.springbootseries.integrationtests.entity;

import java.time.LocalDateTime;

public class Info {

    private Long id;
    private String info;
    private LocalDateTime creationDateTime;

    public Long getId() {
        return id;
    }

    public Info setId(Long id) {
        this.id = id;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Info setInfo(String info) {
        this.info = info;
        return this;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public Info setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }
}
