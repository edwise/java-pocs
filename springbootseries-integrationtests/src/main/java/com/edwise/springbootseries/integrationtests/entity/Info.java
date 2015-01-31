package com.edwise.springbootseries.integrationtests.entity;

import java.time.LocalDateTime;

public class Info {

    private long id;
    private String info;
    private LocalDateTime creationDateTime;

    public long getId() {
        return id;
    }

    public Info setId(long id) {
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
