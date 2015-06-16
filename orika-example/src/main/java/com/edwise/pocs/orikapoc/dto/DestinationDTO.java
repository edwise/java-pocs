package com.edwise.pocs.orikapoc.dto;

import java.time.LocalDate;
import java.util.List;

public class DestinationDTO {

    private Long id;
    private String name;
    private String surname;
    private int type;
    private List<String> options;
    private LocalDate creationDate;
    private Integer firstNum;

    public Long getId() {
        return id;
    }

    public DestinationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DestinationDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public DestinationDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getType() {
        return type;
    }

    public DestinationDTO setType(int type) {
        this.type = type;
        return this;
    }

    public List<String> getOptions() {
        return options;
    }

    public DestinationDTO setOptions(List<String> options) {
        this.options = options;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public DestinationDTO setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Integer getFirstNum() {
        return firstNum;
    }

    public DestinationDTO setFirstNum(Integer firstNum) {
        this.firstNum = firstNum;
        return this;
    }

    @Override
    public String toString() {
        return "DestinationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                ", options=" + options +
                ", creationDate=" + creationDate +
                ", firstNum=" + firstNum +
                '}';
    }
}
