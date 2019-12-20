package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class OrderPageController {
    private static final Logger log = LoggerFactory.getLogger(EditBookCatalogueController.class);
    private final OrderService orderService;

    public OrderPageController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderPage")
    public String doGet(HttpServletRequest req, UsernamePasswordAuthenticationToken principal) {
        User user = (User) principal.getPrincipal();
        if (orderService.findOrderFilledByUserId(user.getId()) == null) {
            return "redirect:/customerPage";
        }
        int orderId = orderService.findOrderFilledByUserId(user.getId()).getId();
        List<Book> booksInOrder = orderService.getBooksByOrderId(orderId);
        if (booksInOrder.isEmpty()){
            req.setAttribute("booksInOrderIsEmpty", true);
            return "orderPage";
        }
        req.setAttribute("booksInOrder", booksInOrder);
        return "orderPage";
    }

    @PostMapping("/orderPage")
    public String deleteBook(HttpServletRequest req, UsernamePasswordAuthenticationToken principal) {
        if (req.getParameter("bookDelete") != null) {
            User user = (User) principal.getPrincipal();
            int bookId = Integer.parseInt(req.getParameter("bookDelete"));
            int orderId = orderService.findOrderFilledByUserId(user.getId()).getId();
            orderService.deleteBookFromOrder(orderId, bookId);
            log.info("book {} deleted from order {} ", bookId, orderId);
        }
        return "redirect:/orderPage";
    }
    @PostMapping("/confirm")
    public String confirm(HttpServletRequest req, UsernamePasswordAuthenticationToken principal){
        User user = (User) principal.getPrincipal();
        Order orderFilled = orderService.findOrderFilledByUserId(user.getId());
        orderService.updateOrderStatus(orderFilled, OrderStatus.FORMED);
        req.setAttribute("confirmed", true);
        return "orderPage";
    }
}
