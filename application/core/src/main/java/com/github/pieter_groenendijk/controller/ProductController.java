package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.repository.ProductRepository;
import com.github.pieter_groenendijk.repository.genre.IGenreRepository;
import com.github.pieter_groenendijk.repository.genre.GenreRepository;
import com.github.pieter_groenendijk.service.IProductService;
import com.github.pieter_groenendijk.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private IProductService productService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private ProductController() {
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        IGenreRepository genreRepository = new GenreRepository(sessionFactory);
        productService = new ProductService(productRepository, genreRepository);
    }

    @Operation(summary = "Get all ProductCopy details by product", description = "Get product details by product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "No product found for the given productId")
    })
    @GetMapping("/{productCopyId}")
    public ResponseEntity<ProductCopy> retrieveProductByCopyId(@PathVariable("productCopyId") long productCopyId) {
        try {
            ProductCopy productCopy = productService.retrieveProductByCopyId(productCopyId);
            return new ResponseEntity<>(productCopy, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
