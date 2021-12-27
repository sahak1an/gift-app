package com.sahakian.reactivemongoconnector.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExampleData implements Serializable {
    private String name;
    private String surname;
}
