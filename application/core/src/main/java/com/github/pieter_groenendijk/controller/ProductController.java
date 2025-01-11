package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.repository.ProductRepository;
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

public class ProductController {
    private IProductService productService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private ProductController() {
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        productService = new ProductService(productRepository);
    }

    @Operation(summary = "Get all reservation details by reservationId", description = "Get reservation details by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "404", description = "No reservation found for the given reservationId\"")
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
