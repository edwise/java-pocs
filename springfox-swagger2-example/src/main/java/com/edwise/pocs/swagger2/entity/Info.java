package com.edwise.pocs.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "Info entity", description = "Complete data of a entity Info")
public class Info {

    @ApiModelProperty(value = "The id of the info", required = false)
    private Long id;

    @ApiModelProperty(value = "The text of the info", required = true)
    private String infoText;

    @ApiModelProperty(value = "The date of the info", required = true)
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
