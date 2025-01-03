package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.product.ProductTemplate;

import java.util.Optional;

public interface IProductService {
    ProductTemplate store(ProductTemplate product);
    Optional<ProductTemplate> deleteProductById(long productId);

    Optional <ProductTemplate> retrieveProductById(long productId);
    ProductTemplate updateProduct(ProductTemplate product);
}
