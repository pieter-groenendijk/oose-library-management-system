package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.repository.ProductRepository;
import com.github.pieter_groenendijk.repository.genre.IGenreRepository;
import com.github.pieter_groenendijk.repository.genre.GenreRepository;
import com.github.pieter_groenendijk.service.IProductService;
import com.github.pieter_groenendijk.service.ProductService;
import org.hibernate.SessionFactory;

public class ProductController {
    private IProductService productService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private ProductController() {
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        IGenreRepository genreRepository = new GenreRepository(sessionFactory);
        productService = new ProductService(productRepository, genreRepository);
    }
}
