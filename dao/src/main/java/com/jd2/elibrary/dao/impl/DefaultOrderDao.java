package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.converter.BookConverter;
import com.jd2.elibrary.dao.converter.OrderConverter;
import com.jd2.elibrary.dao.converter.UserConverter;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.repository.BookJpaRepository;
import com.jd2.elibrary.dao.repository.OrderJpaRepository;
import com.jd2.elibrary.dao.repository.UserJpaRepository;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;

import java.util.List;

public class DefaultOrderDao implements OrderDao {
    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final BookJpaRepository bookJpaRepository;

    public DefaultOrderDao(OrderJpaRepository orderJpaRepository, UserJpaRepository userJpaRepository,
                           BookJpaRepository bookJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = OrderConverter.convertToOrderEntity(order);
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public Order findByOrderStatus(OrderStatus orderStatus) {
        OrderEntity orderEntity = orderJpaRepository.findByOrderStatus(orderStatus);
        return OrderConverter.convertToOrder(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> orderEntities = orderJpaRepository.findAll();
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public List<Order> findAllByUserId(int userId) {
        UserEntity userEntity = userJpaRepository.findById(userId).get();
        List<OrderEntity> orderEntities = orderJpaRepository.findAllByUserEntity(userEntity);
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public List<Order> findOrderByOrderStatusAndUser(OrderStatus orderStatus, int userId) {
        UserEntity userEntity = userJpaRepository.findById(userId).get();
        List<OrderEntity> orderEntities = orderJpaRepository.findOrderEntitiesByOrderStatusAndUserEntity(
                orderStatus, userEntity);
        if (orderEntities.isEmpty()) {
            return null;
        }
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public Order findById(int id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).get();
        return OrderConverter.convertToOrder(orderEntity);
    }

    @Override
    public List<Book> getBooksByOrderId(int orderId) {
        OrderEntity orderEntity = orderJpaRepository.findById(orderId).get();
        List<BookEntity> bookEntities = orderEntity.getBooksInOrder();
        return BookConverter.convertToListBook(bookEntities);
    }

    @Override
    public boolean existsById(int id) {
        return orderJpaRepository.existsById(id);
    }

    @Override
    public boolean existBookInOrder(int orderId, int bookId) {
        List<Book> books = getBooksByOrderId(orderId);
        for (Book b : books) {
            if (b.getId() == bookId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existByUser(User user) {
        UserEntity userEntity = UserConverter.convertToUserEntity(user);
        return orderJpaRepository.existsByUserEntity(userEntity);
    }

    @Override
    public void addBookToOrder(Order order, int bookId) {
        List<Book> books = getBooksByOrderId(order.getId());
        BookEntity bookEntity = bookJpaRepository.findById(bookId).get();
        books.add(BookConverter.convertToBook(bookEntity));
        OrderEntity orderEntity = OrderConverter.convertToOrderEntity(order);
        orderEntity.setBooksInOrder(BookConverter.convertToListBookEntity(books));
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public void deleteBookFromOrder(int orderId, int bookId) {
        List<Book> books = getBooksByOrderId(orderId);
        BookEntity bookEntity = bookJpaRepository.findById(bookId).get();
        for (Book book : books) {
            if (book.getId() == bookEntity.getId()) {
                books.remove(book);
                break;
            }
        }
        OrderEntity orderEntity = orderJpaRepository.findById(orderId).get();
        if (!books.isEmpty()) {
            orderEntity.setBooksInOrder(BookConverter.convertToListBookEntity(books));
            orderJpaRepository.save(orderEntity);
        } else {
            orderJpaRepository.deleteById(orderId);
        }
    }

    @Override
    public void updateOrderStatus(Order order, OrderStatus status) {
        OrderEntity orderEntity = OrderConverter.convertToOrderEntity(order);
        orderEntity.setOrderStatus(status);
        //почему после изменения статуса заказа очищается список книг?
        List<Book> books = getBooksByOrderId(order.getId());
        orderEntity.setBooksInOrder(BookConverter.convertToListBookEntity(books));
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public void deleteById(int id) {
        orderJpaRepository.deleteById(id);
    }
}
