package com.siedge.app.Bookshop.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class AuthorBerezovskyi {
    @Id // mark id variable like KEY for each book
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for any next added book id will increment
    private long id;

    @Column
    @NotBlank(message = "Author name must not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
