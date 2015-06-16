package com.edwise.pocs.orikapoc.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SourceEntity {

    private Long id;
    private String name;
    private String userSurname;
    private String[] options;
    private EntityType entityType;
    private LocalDateTime creationDateTime;
    private Integer[] nums;

    public Long getId() {
        return id;
    }

    public SourceEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SourceEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public SourceEntity setUserSurname(String userSurname) {
        this.userSurname = userSurname;
        return this;
    }

    public String[] getOptions() {
        return options;
    }

    public SourceEntity setOptions(String[] options) {
        this.options = options;
        return this;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public SourceEntity setEntityType(EntityType entityType) {
        this.entityType = entityType;
        return this;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public SourceEntity setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }

    public Integer[] getNums() {
        return nums;
    }

    public SourceEntity setNums(Integer[] nums) {
        this.nums = nums;
        return this;
    }

    @Override
    public String toString() {
        return "SourceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", options=" + Arrays.toString(options) +
                ", entityType=" + entityType +
                ", creationDateTime=" + creationDateTime +
                ", nums=" + Arrays.toString(nums) +
                '}';
    }
}
