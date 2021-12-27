package com.sahakian.reactivemongoconnector.listener;

import java.time.LocalDate;

import com.sahakian.reactivemongoconnector.data.MongoDataObject;
import com.sahakian.reactivemongoconnector.service.idgenerator.SnowFlakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SnowflakeMongoListener extends AbstractMongoEventListener<MongoDataObject> {
    private final SnowFlakeIdGenerator idGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<MongoDataObject> event) {
        var dataObject = event.getSource();
        dataObject.setId(idGenerator.getUuid());
        dataObject.setTimestamp(LocalDate.now());
    }
}
