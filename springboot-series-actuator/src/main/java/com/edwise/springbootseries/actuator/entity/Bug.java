package com.edwise.springbootseries.actuator.entity;

import java.time.LocalDateTime;

public class Bug {

    private long id;
    private String name;
    private String description;
    private LocalDateTime date;

    public long getId() {
        return id;
    }

    public Bug setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bug setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Bug setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Bug setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
