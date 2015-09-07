package com.edwise.pocs.jerseyrestclient.model;

import java.time.LocalDateTime;

public class Info {

    private Long id;
    private String infoText;
    private LocalDateTime creationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", infoText='" + infoText + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
