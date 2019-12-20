package com.jd2.elibrary.service.impl.impl;


import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ResourceBundle;

@Service
public class DefaultBookService implements BookService {

    public DefaultBookService(BookDao defaultBookDao) {
        this.defaultBookDao = defaultBookDao;
    }

    private final BookDao defaultBookDao;
    private static final Logger log = LoggerFactory.getLogger(DefaultBookService.class);


    @Override
    @Transactional
    public List<Book> paging(int pageNumber) {
        return defaultBookDao.paging(pageNumber);
    }


    @Override
    @Transactional
    public Book findById(int id) {
        return defaultBookDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Book book) {
        defaultBookDao.save(book);
    }

    @Override
    @Transactional
    public void decrCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = defaultBookDao.findById(id).getCount();
        //уменьшенное кол-во
        int newCount = oldCount - count;
        //устанавливаем уменьшенное кол-во
        if (newCount >=0) {
            defaultBookDao.updateCount(id, newCount);
        } else {
            log.error("Введенное количество больше имеющегося в наличии! Изменения не внесены.");
        }

    }

    @Override
    @Transactional
    public void incrCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = defaultBookDao.findById(id).getCount();
        //увеличенное кол-во
        int newCount = oldCount + count;
        //устанавливаем увеличенное кол-во
        defaultBookDao.updateCount(id, newCount);
    }

    @Override
    @Transactional
    public int countPageBooks() {
        int count = defaultBookDao.count();
        ResourceBundle resource = ResourceBundle.getBundle("methodConst");
        int pageSize = Integer.parseInt(resource.getString("pageSize"));
        return (int)Math.ceil((double)count/pageSize);
    }
}
