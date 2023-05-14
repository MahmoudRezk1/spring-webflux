package com.global.springwebflux.config;

import com.global.springwebflux.entity.Product;
import com.global.springwebflux.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@Log4j2
public class AppStartup implements CommandLineRunner {
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========>> in Command Line Runner");
        try {
            productService.update(new Product(null, "product 1", 333.5, "category 1"));
            productService.update(new Product(null, "product 2", 333.5, "category 1"));
            productService.update(new Product(null, "product 3", 333.5, "category 2"));
            productService.update(new Product(null, "product 4", 333.5, "category 2"));
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
