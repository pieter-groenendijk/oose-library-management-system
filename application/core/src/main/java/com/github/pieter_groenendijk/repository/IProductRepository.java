package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductTemplate;

import java.util.Optional;

public interface IProductRepository {
    ProductTemplate store(ProductTemplate product);

    Optional<ProductTemplate> deleteProductById(long productId);

    Optional<ProductTemplate> retrieveProductById(long productId);

    Optional<ProductTemplate> retrieveProductByCopyId(long productCopyId);

    ProductTemplate updateProduct(ProductTemplate product);

}
