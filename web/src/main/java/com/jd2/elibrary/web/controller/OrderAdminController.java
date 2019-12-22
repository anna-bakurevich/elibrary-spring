package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class OrderAdminController {
    private static final Logger log = LoggerFactory.getLogger(LibrarianPageController.class);

    private final OrderService orderService;

    public OrderAdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderAdmin")
    public String ordersList(HttpServletRequest req) {
        List<Order> orders = orderService.findAll();
        req.setAttribute("orders", orders);
        return "orderAdmin";
    }

    @PostMapping("/issue")
    public String issue(HttpServletRequest req) {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        orderService.updateOrderStatus(orderService.findById(orderId), OrderStatus.ISSUED);
        log.info("order {} issued {}", orderId, LocalDateTime.now());
        return "redirect:/orderAdmin";
    }

    @PostMapping("/return")
    public String returnOrder(HttpServletRequest req) {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        orderService.updateOrderStatus(orderService.findById(orderId), OrderStatus.RETURNED);
        log.info("order {} returned {}", orderId, LocalDateTime.now());
        return "redirect:/orderAdmin";
    }

    @PostMapping("/blackList")
    public String blackList(HttpServletRequest req) {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        orderService.updateOrderStatus(orderService.findById(orderId), OrderStatus.BLACKLIST);
        log.info("order {} moved to black list {}", orderId, LocalDateTime.now());
        return "redirect:/orderAdmin";
    }

    @PostMapping("/orderDetails")
    public String orderDetails(HttpServletRequest req){
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.findById(orderId);
        req.setAttribute("orderId",orderId);
        req.setAttribute("orderDate", order.getOrderDate());
        req.setAttribute("returnDate", order.getReturnDate());
        req.setAttribute("status", order.getOrderStatus());
        List<Book> books = orderService.getBooksByOrderId(orderId);
        req.setAttribute("books", books);
        return "orderDetails";
    }
}
