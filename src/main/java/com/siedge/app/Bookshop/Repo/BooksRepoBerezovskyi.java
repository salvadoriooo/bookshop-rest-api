package com.siedge.app.Bookshop.Repo;

import com.siedge.app.Bookshop.Models.BooksBerezovskyi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;




public interface BooksRepoBerezovskyi extends JpaRepository<BooksBerezovskyi, Long> {
    Page<BooksBerezovskyi> findByTitleBerezovskyiContainingIgnoreCase(String title, Pageable pageable);

}
