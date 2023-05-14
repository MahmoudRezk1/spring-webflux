package com.global.springwebflux.controller;

import com.global.springwebflux.entity.Product;
import com.global.springwebflux.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public Mono<Product> findById(@PathVariable String id){
        return productService.findById(id);
    }
    @GetMapping(path = "/name/{name}")
    public Flux<Product> getProductByName(@PathVariable String name){
        return productService.findByName(name);
    }
    @GetMapping(produces = {"text/event-stream"})
    public Flux<Product> findAll(){
        return productService.findAll();
    }
    @PostMapping
    public Mono<Product> insert(@RequestBody Product product){
        return productService.insert(product);
    }
    @PutMapping
    public Mono<Product> update(@RequestBody Product product){
        return productService.update(product);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return productService.deleteById(id);
    }
    

    @GetMapping(path = "/count")
    public Integer getProductCount(){
        return 20;
    }
    @GetMapping(path = "/count-reactive",produces = {"text/event-stream"})
    public Mono<ResponseEntity<?>> getCountReactive(){
        //mono return zero or one element
        return Mono.just(ResponseEntity.ok(20));
    }
    @GetMapping(path = "/all",produces = {"text/event-stream"})
    public List<Integer> getAll() throws InterruptedException {
        // flux return more than one element
        List<Integer> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(i+1);
            Thread.sleep(500);
        }
        return products;
    }
    @GetMapping(path = "/all-reactive",produces = {"text/event-stream"})
    public Flux<Integer> getAllReactive() {
        // flux return more than one element
        return Flux.create(
                fluxList -> {
                    for (int i = 0; i < 20; i++) {
                        fluxList.next(i+1);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.fillInStackTrace();
                        }
                    }
                    fluxList.complete();
                }
        );
    }
}
