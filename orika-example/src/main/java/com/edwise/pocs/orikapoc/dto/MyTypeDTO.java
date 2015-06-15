package com.edwise.pocs.orikapoc.dto;

public enum MyTypeDTO {
    TYPE_0(0),
    TYPE_1(1),
    TYPE_2(2);

    private int typeId;

    MyTypeDTO(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
