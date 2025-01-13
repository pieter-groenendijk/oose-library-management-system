package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
import com.github.pieter_groenendijk.model.product.Genre;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductTemplate store(ProductTemplate product);
    Optional<ProductTemplate> deleteProductById(long productId);

    Optional <ProductTemplate> retrieveProductById(long productId);
    ProductTemplate updateProduct(ProductTemplate product);

    ProductCopy updateProductCopy(ProductCopy productCopy);

    //Genre
    Genre retrieveGenreById(long id);
    void store(Genre genre);
    void update(long id, Genre genre);
    List<Genre> retrieveGenreList();
}
