package com.siedge.app.Bookshop.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// this annotation is bean type for notice that we have to create a table in our database like object...
@Entity
public class BooksBerezovskyi {

    //. with next data
    @Id // mark id variable like KEY for each book
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for any next added book id will increment
    private long id;

    // mark column via column annotation for our books
    @Column
    @NotBlank(message = "Title must not be empty")
    private String titleBerezovskyi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    @NotNull(message = "Author must be specified")
    private AuthorBerezovskyi authorBerezovskyi;

    @Column
    @Min(value = 0, message="Quantity must not be negative")
    private int quantityBerezovskyi;

    @Column
    @Positive(message = "Price must be greater than 0")
    private int priceBerezovskyi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleBerezovskyi() {
        return titleBerezovskyi;
    }

    public void setTitleBerezovskyi(String titleBerezovskyi) {
        this.titleBerezovskyi = titleBerezovskyi;
    }

    public AuthorBerezovskyi getAuthorBerezovskyi() {
        return authorBerezovskyi;
    }

    public void setAuthorBerezovskyi(AuthorBerezovskyi authorBerezovskyi) {
        this.authorBerezovskyi = authorBerezovskyi;
    }

    public int getQuantityBerezovskyi() {
        return quantityBerezovskyi;
    }

    public void setQuantityBerezovskyi(int quantityBerezovskyi) {
        this.quantityBerezovskyi = quantityBerezovskyi;
    }

    public int getPriceBerezovskyi() {
        return priceBerezovskyi;
    }

    public void setPriceBerezovskyi(int priceBerezovskyi) {
        this.priceBerezovskyi = priceBerezovskyi;
    }
}
