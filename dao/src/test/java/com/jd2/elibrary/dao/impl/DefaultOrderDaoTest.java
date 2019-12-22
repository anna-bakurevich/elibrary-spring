package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.config.DaoConfig;
import com.jd2.elibrary.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultOrderDaoTest {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;

    @BeforeEach
    public void init() {
        Order order = new Order();
        User user = userDao.findById(4);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setReturnDate(LocalDate.now().plusDays(30));
        order.setOrderStatus(OrderStatus.FILLED);
        orderDao.save(order);

    }

    @Transactional
    @Test
    void findByOrderStatusTest() {
        List<Order> orders = orderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, 4);
        assertNotNull(orders);
    }

    @Transactional
    @Test
    void findAllTest() {
        List<Order> orders = orderDao.findAll();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        System.out.println(orders);
    }

    @Transactional
    @Test
    void findAllByUserIdTest() {

        List<Order> orders = orderDao.findAllByUserId(4);

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Transactional
    @Test
    void findOrderByOrderStatusAndUserTest() {
        List<Order> ordersFilled = orderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, 4);
        assertNotNull(ordersFilled);
        assertEquals(1, ordersFilled.size());
        assertEquals("Иван", ordersFilled.get(0).getUser().getFirstName());
    }


    @Transactional
    @Test
    void existByUserTest() {
        User user = userDao.findById(4);
        assertTrue(orderDao.existByUser(user));
    }

    @Transactional
    @Test
    void deleteByIdTest() {
        int orderId = orderDao.findAllByUserId(4).get(0).getId();
        orderDao.deleteById(orderId);
        assertFalse(orderDao.existsById(orderId));
    }

    @Transactional
    @Test
    void updateByOrderStatus(){
        Order order = orderDao.findAllByUserId(4).get(0);
        orderDao.updateOrderStatus(order, OrderStatus.BLACKLIST);
        assertEquals(OrderStatus.BLACKLIST, orderDao.findById(order.getId()).getOrderStatus());
    }

}
