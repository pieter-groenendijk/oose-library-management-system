package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.product.Genre;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.repository.genre.IGenreRepository;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final IGenreRepository genreRepository;


    public ProductService(IProductRepository productRepository, IGenreRepository genreRepository) {
        this.productRepository = productRepository;
        this.genreRepository = genreRepository;
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

    @Override
    public ProductCopy updateProductCopy(ProductCopy productCopy) {
        if (productCopy == null || productCopy.getProductCopyId() == 0) {
            throw new IllegalArgumentException("ProductCopy and productCopy ID must be provided for update");
        }

        return productRepository.updateProductCopy(productCopy);
    }

    @Override
    public ProductCopy retrieveProductByCopyId(long productCopyId) {
    return productRepository.retrieveProductCopyById(productCopyId)
            .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productCopyId + " not found."));
    }


    public Genre retrieveGenreById(long id){
        return genreRepository.retrieveGenreById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre with ID " + id + " not found."));
    }

    public void store(Genre genre) {
        genreRepository.store(genre);
    }

    public void update(long id, Genre genre) {
        genreRepository.update(genre);
    }

    public List<Genre> retrieveGenreList() {
        return genreRepository.retrieveGenreList();
    }
}
