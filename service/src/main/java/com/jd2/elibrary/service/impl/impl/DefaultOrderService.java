package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderDao defaultOrderDao;

    public DefaultOrderService(OrderDao defaultOrderDao) {
        this.defaultOrderDao = defaultOrderDao;
    }


    @Override
    @Transactional
    public void save(Order order) {
        defaultOrderDao.save(order);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return defaultOrderDao.findAll();
    }

    @Override
    @Transactional
    public void addBookToOrder(Order order, int bookId) {
        defaultOrderDao.addBookToOrder(order, bookId);
    }

    @Override
    public void deleteBookFromOrder(int orderId, int bookId) {
        defaultOrderDao.deleteBookFromOrder(orderId, bookId);
    }

    @Override
    public void updateOrderStatus(Order order, OrderStatus status) {
        defaultOrderDao.updateOrderStatus(order, status);
    }

    @Override
    @Transactional
    public List<Book> getBooksByOrderId(int orderId) {
        return defaultOrderDao.getBooksByOrderId(orderId);
    }

    @Override
    public boolean existBookInOrder(int orderId, int bookId) {
        return defaultOrderDao.existBookInOrder(orderId, bookId);
    }

    @Override
    @Transactional
    public boolean existById(int id) {
        return defaultOrderDao.existsById(id);
    }

    @Override
    @Transactional
    public boolean existByUser(User user){
        return defaultOrderDao.existByUser(user);
    }

    @Override
    @Transactional
    public List<Order> findAllByUserId(int userId) {
        return defaultOrderDao.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Order findOrderFilledByUserId(int userId) {
        if (defaultOrderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, userId) == null) {
            return null;
        }
        return defaultOrderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, userId).get(0);
    }


}
