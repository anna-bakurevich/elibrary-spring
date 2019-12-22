package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBookServiceTest {
    @Mock
    BookDao dao;

    @InjectMocks
    DefaultBookService service;

    @Test
    void getByIdTest() {
        Book book = new Book();
        book.setId(1);
        when(dao.findById(1)).thenReturn(book);
        assertNotNull(service.findById(1));
        assertNull(service.findById(100));
    }

    @Test
    void saveBookTest() {
        Book book = new Book();
        service.save(book);
        verify(dao).save(book);
    }

    @Test
    void pagingTest(){
        service.paging(2);
        verify(dao).paging(2);
    }

    @Test
    void countPageBooksTest(){
        service.countPageBooks();
        verify(dao).count();
    }

    @Test
    void decrCountBookTest(){
        Book book = new Book();
        book.setId(1);
        book.setCount(10);
        dao.save(book);
        when(dao.findById(1)).thenReturn(book);
        service.decrCountBook(1, 3);
        verify(dao).updateCount(1, 7);
    }
    @Test
    void incrCountBookTest(){
        Book book = new Book();
        book.setId(1);
        book.setCount(10);
        dao.save(book);
        when(dao.findById(1)).thenReturn(book);
        service.incrCountBook(1, 3);
        verify(dao).updateCount(1, 13);
    }

}