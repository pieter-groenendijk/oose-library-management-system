package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;

import java.util.Optional;

public interface IProductRepository {
    ProductTemplate store(ProductTemplate product);

    Optional<ProductTemplate> deleteProductById(long productId);

    Optional<ProductTemplate> retrieveProductById(long productId);

    ProductTemplate updateProduct(ProductTemplate product);

    Optional<ProductCopy> retrieveProductCopyById(long productCopyId);


    ProductCopy updateProductCopy(ProductCopy productCopy);
}
