package com.edwise.pocs.lombok.withlombok;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Builder;

@Data
@Accessors(chain = true)
@Builder
public class Book {

    private Long id;
    private String title;
    private String isbn;
    private Short type;
}
