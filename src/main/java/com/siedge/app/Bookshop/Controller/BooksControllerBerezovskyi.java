package com.siedge.app.Bookshop.Controller;

import com.siedge.app.Bookshop.Models.AuthorBerezovskyi;
import com.siedge.app.Bookshop.Models.BooksBerezovskyi;
import com.siedge.app.Bookshop.Repo.AuthorRepoBerezovskyi;
import com.siedge.app.Bookshop.Repo.BooksRepoBerezovskyi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RestController
public class BooksControllerBerezovskyi {

    @Autowired
    private AuthorRepoBerezovskyi authorRepoBerezovskyi;
    // mark by annotation autowired for give bean template to BooksRepoBerezvoskyi
    @Autowired
    private BooksRepoBerezovskyi booksRepoBerezovskyi;
    //cRud - reading

    // testing message for http://localhost:8080
    @GetMapping
    public String getMessage () {
        return "Hello, Siedge";
    }

    // show all books from database http://localhost:8080/books
    @GetMapping("/books")
    public Page<BooksBerezovskyi> getBooks(@RequestParam(required = false) String title, Pageable pageable) {
        if (title == null) {
            return booksRepoBerezovskyi.findAll(pageable);
        }
        return booksRepoBerezovskyi.findByTitleBerezovskyiContainingIgnoreCase(title,pageable);
    }

    @GetMapping ("/books/{id}")
    public BooksBerezovskyi getBookById (@PathVariable long id) {
        BooksBerezovskyi getBook = booksRepoBerezovskyi.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found"));
        return getBook;
    }

    // post new book to list http://localhost:8080/save
    @PostMapping ("/save")

    @ResponseStatus(HttpStatus.CREATED)
    public BooksBerezovskyi saveBooks(@Valid @RequestBody BooksBerezovskyi booksBerezovskyi) {
        long authorId = booksBerezovskyi.getAuthorBerezovskyi().getId();
        AuthorBerezovskyi author = authorRepoBerezovskyi.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + authorId + " not found"));
        booksBerezovskyi.setAuthorBerezovskyi(author);
        return booksRepoBerezovskyi.save(booksBerezovskyi);
    }

    // edit data in the existing book in database
    @PutMapping(value = "/edit/{id}")  //http://localhost:8080/edit/{book's id}

    /*get data via JPARepository of our book to map and edit. Annotation PathVariable is needed to find element with id book to edit*/
    public BooksBerezovskyi editBooksBerezovskyi(@PathVariable long id, @Valid @RequestBody BooksBerezovskyi booksBerezovskyi) {
        BooksBerezovskyi editedBookBerezovskyi = booksRepoBerezovskyi.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found"));

        long authorId = booksBerezovskyi.getAuthorBerezovskyi().getId();
        AuthorBerezovskyi author = authorRepoBerezovskyi.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + authorId + " not found"));

        editedBookBerezovskyi.setTitleBerezovskyi(booksBerezovskyi.getTitleBerezovskyi());
        editedBookBerezovskyi.setAuthorBerezovskyi(author);
        editedBookBerezovskyi.setQuantityBerezovskyi(booksBerezovskyi.getQuantityBerezovskyi());
        editedBookBerezovskyi.setPriceBerezovskyi(booksBerezovskyi.getPriceBerezovskyi());
        return booksRepoBerezovskyi.save(editedBookBerezovskyi);
    }

    // delete any book in database
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooks(@PathVariable long id){
        BooksBerezovskyi deleteBook = booksRepoBerezovskyi.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found"));
        booksRepoBerezovskyi.delete(deleteBook);

    }
}
