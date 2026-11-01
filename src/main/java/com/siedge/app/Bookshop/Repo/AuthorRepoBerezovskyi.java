package com.siedge.app.Bookshop.Repo;

import com.siedge.app.Bookshop.Models.AuthorBerezovskyi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepoBerezovskyi extends JpaRepository<AuthorBerezovskyi, Long> {

    Long id(long id);
}
