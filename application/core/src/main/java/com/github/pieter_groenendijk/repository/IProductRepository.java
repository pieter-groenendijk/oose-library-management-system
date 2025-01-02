package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductTemplate;

import java.util.Optional;

public interface IProductRepository {
    ProductTemplate store(ProductTemplate product);
    Optional<ProductTemplate> DeleteProductById(long productId);
    ProductTemplate retrieveProductById(long productId);
    ProductTemplate updateProduct(ProductTemplate product);

}
