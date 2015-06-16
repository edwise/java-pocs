package com.edwise.pocs.orikapoc.entity;

public class EntityType {

    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public EntityType setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public EntityType setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "EntityType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
