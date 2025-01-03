package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.product.ProductTemplate;
import com.github.pieter_groenendijk.repository.IProductRepository;

import java.util.Optional;

public class ProductService implements IProductService {
    private IProductRepository productRepository;


    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductTemplate store(ProductTemplate product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        return productRepository.store(product);
    }

    @Override
    public Optional<ProductTemplate> deleteProductById(long productId) {
        return productRepository.deleteProductById(productId);
    }

    @Override
    public Optional<ProductTemplate> retrieveProductById(long productId) {
        return productRepository.retrieveProductById(productId);
    }

    @Override
    public ProductTemplate updateProduct(ProductTemplate product) {
        if (product == null || product.getProductId() == 0) {
            throw new IllegalArgumentException("Product and product ID must be provided for update");
        }

        return productRepository.updateProduct(product);
    }
}
