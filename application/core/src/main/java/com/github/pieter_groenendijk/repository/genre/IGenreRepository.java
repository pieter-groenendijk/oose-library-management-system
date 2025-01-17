package com.github.pieter_groenendijk.repository.genre;

import com.github.pieter_groenendijk.model.product.Genre;

import java.util.List;
import java.util.Optional;

public interface IGenreRepository {

    Optional<Genre> retrieveGenreById(long id);
    List<Genre> retrieveGenreList();
    void store(Genre genre);
    void update(Genre genre);
}