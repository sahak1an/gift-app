package com.sahakian.reactivemongoconnector.data;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MongoDataObject implements Serializable {

    @Id
    private String id;

    private Object data;

    private LocalDate timestamp;
}
