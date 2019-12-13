package com.jd2.elibrary.dao.repository;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByOrderStatus(OrderStatus orderStatus);

    List<OrderEntity> findAllByUserEntity(UserEntity userEntity);

    List<OrderEntity> findOrderEntitiesByOrderStatus(OrderStatus orderStatus);

    List<OrderEntity> findOrderEntitiesByOrderStatusAndUserEntity(OrderStatus orderStatus, UserEntity userEntity);

    boolean existsByUserEntity(UserEntity userEntity);

}
