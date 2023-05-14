package com.global.springwebflux.service;

import com.global.springwebflux.entity.Product;
import com.global.springwebflux.repository.ProductRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public Mono<Product> findById(String id){
        return productRepo.findById(id);
    }
    public Flux<Product> findByName(String name){
        return productRepo.findByName(name);
    }
    public Flux<Product> findAll(){
        return productRepo.findAll();
    }
    public Mono<Product> insert(Product product){
        return productRepo.insert(product);
    }
    public Mono<Product> update(Product product){
        return productRepo.save(product);
    }
    public Mono<Void> deleteById(String id){
        return productRepo.deleteById(id);
    }

}
