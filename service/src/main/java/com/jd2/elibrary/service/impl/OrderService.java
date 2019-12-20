package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;

import java.util.List;

public interface OrderService {

    void save(Order order);

    List<Order> findAll();

    void addBookToOrder(Order order, int bookId);

    void deleteBookFromOrder(int orderId, int bookId);

    void updateOrderStatus(Order order, OrderStatus status);

    List<Book> getBooksByOrderId(int orderId);

    boolean existBookInOrder(int orderId, int bookId);

    boolean existById(int userId);

    Order findById(int id);

    boolean existByUser(User user);

    List<Order> findAllByUserId(int userId);

    Order findOrderFilledByUserId(int userId);
}
