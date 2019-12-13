package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.User;

import java.util.List;

public interface OrderService {

    void save(Order order);

    List<Order> findAll();

    void addBookToOrder(Order order, int bookId);

    void deleteBookFromOrder(int orderId, int bookId);

    List<Book> getBooksByOrderId(int orderId);

    boolean existBookInOrder(int orderId, int bookId);

    boolean existById(int userId);

    boolean existByUser(User user);

    List<Order> findAllByUserId(int userId);

    Order findOrderFilledByUserId(int userId);
}
