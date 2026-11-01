package com.siedge.app.Bookshop.Controller;

import com.siedge.app.Bookshop.Models.AuthorBerezovskyi;
import com.siedge.app.Bookshop.Repo.AuthorRepoBerezovskyi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorControllerBerezovskyi {
    @Autowired
    private AuthorRepoBerezovskyi authorRepoBerezovskyi;

    @GetMapping ("/authors")
    public List<AuthorBerezovskyi> getAuthors(){
        return authorRepoBerezovskyi.findAll();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorBerezovskyi saveAuthor(@Valid @RequestBody AuthorBerezovskyi authorBerezovskyi)  {
        return authorRepoBerezovskyi.save(authorBerezovskyi);
    }

}
