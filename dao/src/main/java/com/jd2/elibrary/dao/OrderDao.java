package com.jd2.elibrary.dao;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;

import java.util.List;

public interface OrderDao {
    //create
    void save(Order order);

    //read
    Order findByOrderStatus(OrderStatus orderStatus);

    List<Order> findAll();

    List<Order> findAllByUserId(int userId);

    List<Order> findOrderByOrderStatusAndUser(OrderStatus orderStatus, int userId);

    Order findById(int id);

    List<Book> getBooksByOrderId(int orderId);

    boolean existsById(int id);

    boolean existBookInOrder(int orderId, int bookId);

    boolean existByUser(User user);

    //update
    //добавление книги в заказ по bookId
    void addBookToOrder(Order order, int bookId);
    //удаление книги из заказа по bookId
    void deleteBookFromOrder(int orderId, int bookId);
    void updateOrderStatus(Order order, OrderStatus status);

    //delete
    void deleteById(int id);


}
