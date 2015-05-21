package com.edwise.pocs.itrestassured.entity;

import java.time.LocalDateTime;

public class Info {

    private Long id;
    private String infoText;
    private LocalDateTime creationDateTime;

    public Long getId() {
        return id;
    }

    public Info setId(Long id) {
        this.id = id;
        return this;
    }

    public String getInfoText() {
        return infoText;
    }

    public Info setInfoText(String infoText) {
        this.infoText = infoText;
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
