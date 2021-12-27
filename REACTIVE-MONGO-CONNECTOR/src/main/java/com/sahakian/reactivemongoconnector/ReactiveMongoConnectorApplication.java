package com.sahakian.reactivemongoconnector;

import com.sahakian.reactivemongoconnector.data.ExampleData;
import com.sahakian.reactivemongoconnector.data.MongoDataObject;
import com.sahakian.reactivemongoconnector.service.streaming.BaseStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@EnableReactiveMongoRepositories
@SpringBootApplication
public class ReactiveMongoConnectorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoConnectorApplication.class, args);
    }

    @Autowired
    private BaseStreamingService baseStreamingService;

    @Override
    public void run(String... args) {
        MongoDataObject data = new MongoDataObject();
        data.setData(new ExampleData("asd","asd"));
        baseStreamingService.saveAll(Flux.just(data)).subscribe();
    }
}
