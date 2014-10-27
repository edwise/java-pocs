package com.edwise.pocs.lombok.withlombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    private int type;
    private LocalDate birthDate;
    private String phoneNumber;
    private List<String> favoriteBooks;
}
