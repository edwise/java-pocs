package com.edwise.springbootseries.jackson.entity;

import lombok.Data;
import lombok.experimental.Accessors;

//import java.time.LocalDateTime;
import org.joda.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Info {

    private long id;
    private String info;
    private LocalDateTime creationDateTime;
}
