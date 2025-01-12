package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;

    @Column(name = "description", length = 150)
    private String description;

    public long getGenreId() { return genreId; }
    public void setGenreId(long genreId) {this.genreId = genreId; }
    public String getDescription() {return description; }
    public void setDescription(String description) {this.description = description; }
}