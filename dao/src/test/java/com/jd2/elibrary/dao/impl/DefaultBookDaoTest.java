package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.config.DaoConfig;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.BookGenre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultBookDaoTest {

    @Autowired
    private BookDao dao;

    @BeforeEach
    public void init() {
        Book book = new Book();
        book.setAuthorFirstName("Герберт");
        book.setAuthorLastName("Шилдт");
        book.setIsbn("9785604004364");
        book.setTitle("Java. Полное руководство. Десятое издание");
        book.setGenre(BookGenre.SCIENTIFIC);
        book.setCount(1);

        dao.save(book);
    }

    @Transactional
    @Test
    void findByIsbnTest() {
        Book book = dao.findByIsbn("9785604004364");
        assertEquals("Шилдт", book.getAuthorLastName());
    }

    @Transactional
    @Test
    void deleteByIdTest() {
        Book book = dao.findByIsbn("9785604004364");
        dao.deleteById(book.getId());
        assertNull(dao.findByIsbn("9785604004364"));
    }

    @Transactional
    @Test
    void countTest() {
        List books = dao.findAll();
        assertEquals(books.size(), dao.count());
    }

    @Transactional
    @Test
    void findAll() {
        List books = dao.findAll();
        assertNotNull(books);
    }

    @Transactional
    @Test
    void findByIdTest() {
        int id = dao.findByIsbn("9785604004364").getId();
        Book book = dao.findById(id);
        assertEquals("Шилдт", book.getAuthorLastName());
    }

    @Transactional
    @Test
    void pagingTest() {
        List<Book> books = dao.paging(3,2);
        for (Book b : books) {
            System.out.println(b.getTitle());
        }
        assertNotNull(books);
        assertEquals(2, books.size());


    }

    @Test
    void updateCountTest() {
        int id = dao.findByIsbn("9785604004364").getId();

        dao.updateCount(id, 1000);

        final Book bookAfterUpdate = dao.findById(id);
        assertEquals(1000, bookAfterUpdate.getCount());

        dao.deleteById(id);
    }
}

