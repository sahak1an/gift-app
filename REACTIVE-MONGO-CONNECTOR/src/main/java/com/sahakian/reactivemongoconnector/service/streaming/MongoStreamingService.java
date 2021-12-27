package com.sahakian.reactivemongoconnector.service.streaming;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoStreamingService<T,ID> {
    Mono<T> streamOne(ID id);
    Flux<T> streamAll();
    Flux<T> streamByValue(String value);

    Mono<T> streamFirst();
    Mono<T> streamAny();

    T saveOne(Mono<T> item);
    Flux<T> saveAll(Flux<T> items);
}
