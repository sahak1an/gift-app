package com.sahakian.reactivemongoconnector.repository;

import com.sahakian.reactivemongoconnector.data.MongoDataObject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDataObjectRepository extends ReactiveMongoRepository<MongoDataObject,String> {
}
