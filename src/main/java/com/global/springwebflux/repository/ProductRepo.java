package com.global.springwebflux.repository;

import com.global.springwebflux.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product,String> {

    Flux<Product> findByName(String name);
}
