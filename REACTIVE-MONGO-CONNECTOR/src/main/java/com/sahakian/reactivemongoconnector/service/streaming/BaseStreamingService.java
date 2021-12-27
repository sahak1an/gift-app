package com.sahakian.reactivemongoconnector.service.streaming;

import com.sahakian.reactivemongoconnector.data.MongoDataObject;
import com.sahakian.reactivemongoconnector.repository.MongoDataObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseStreamingService implements MongoStreamingService<MongoDataObject,String> {
    private final MongoDataObjectRepository baseMongoRepository;

    @Override
    public Mono<MongoDataObject> streamOne(String id) {
        return baseMongoRepository.findById(id);
    }

    @Override
    public Flux<MongoDataObject> streamAll() {
        return baseMongoRepository.findAll();
    }

    @Override
    public Flux<MongoDataObject> streamByValue(String value) {
        return null;
    }

    @Override
    public Mono<MongoDataObject> streamFirst() {
        return null;
    }

    @Override
    public Mono<MongoDataObject> streamAny() {
        return null;
    }

    @Override
    public MongoDataObject saveOne(Mono<MongoDataObject> item) {
        return null;
    }

    @Override
    public Flux<MongoDataObject> saveAll(Flux<MongoDataObject> items) {
        return baseMongoRepository.saveAll(items.toIterable(16));
    }
}
