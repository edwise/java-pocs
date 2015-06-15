package com.edwise.pocs.orikapoc.dto;

import java.time.LocalDate;
import java.util.List;

public class DestinationDTO {

    private Long id;
    private String name;
    private String surname;
    private MyTypeDTO type;
    private List<String> options;
    private LocalDate creationDate;
}
