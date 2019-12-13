package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class OrderPageController {
    //private static final Logger log = LoggerFactory.getLogger(EditBookCatalogueController.class);
    private final OrderService orderService;
    private final BookService bookService;

    public OrderPageController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/orderPage")
    public String doGet(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("login");
        int orderId = orderService.findOrderFilledByUserId(user.getId()).getId();
        List<Book> booksInOrder = orderService.getBooksByOrderId(orderId);
        System.out.println(booksInOrder);
        req.setAttribute("booksInOrder", booksInOrder);
        return "orderPage";
    }

    @PostMapping("/orderPage")
    public String doPost(HttpServletRequest req) {
        if (req.getParameter("bookDelete") != null) {
            System.out.println("Сработала кнопка delete");
            User user = (User) req.getSession().getAttribute("login");
            int bookId = Integer.parseInt(req.getParameter("bookDelete"));
            int orderId = orderService.findOrderFilledByUserId(user.getId()).getId();
            orderService.deleteBookFromOrder(orderId, bookId);
        }
        return "redirect:/orderPage";
    }
}
