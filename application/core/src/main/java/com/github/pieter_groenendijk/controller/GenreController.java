package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.ProductService;
import com.github.pieter_groenendijk.service.IProductService;
import com.github.pieter_groenendijk.repository.genre.GenreRepository;
import com.github.pieter_groenendijk.repository.genre.IGenreRepository;
import com.github.pieter_groenendijk.repository.ProductRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.model.product.Genre;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private IProductService productService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private GenreController() {
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        IGenreRepository genreRepository = new GenreRepository(sessionFactory);
        productService = new ProductService(productRepository, genreRepository);
    }

    @Operation(summary = "Retrieve a genre", description = "Retrieve a genre by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre found"),
            @ApiResponse(responseCode = "404", description = "Genre not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveGenreById(@PathVariable("id") long id) {
        Genre genre = productService.retrieveGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @Operation(summary = "Create a genre", description = "Add a new genre to the database")
    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        productService.store(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a genre", description = "Change a genre ")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable("id") long id, @RequestBody Genre genre) {
        productService.update(id, genre);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of genres", description = "Retrieve a list of genres")
    @GetMapping("/getAll")
    public ResponseEntity<List<Genre>> retrieveGenreList() {
        List<Genre> genres = productService.retrieveGenreList();
        if (genres.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(genres);
        }
    }

}