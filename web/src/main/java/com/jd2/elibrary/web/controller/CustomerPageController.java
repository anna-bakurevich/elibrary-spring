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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class CustomerPageController {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageController.class);

    private final BookService bookService;
    private final OrderService orderService;

    public CustomerPageController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/customerPage")
    public String doGet(HttpServletRequest req,  UsernamePasswordAuthenticationToken principal) {
        int page = 0;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Book> books = bookService.paging(page);
        int maxNumber = bookService.countPageBooks()-1;

        req.setAttribute("books", books);
        req.setAttribute("maxNumber", maxNumber);
        req.setAttribute("page", page);
        req.setAttribute("order", true);
        User user = (User) principal.getPrincipal();
        Order orderFilled = orderService.findOrderFilledByUserId(user.getId());
        if (orderFilled == null){
            req.setAttribute("order", false);
            return "customerPage";
        }
        return "customerPage";
    }

    @PostMapping("/customerPage")
    public String page(HttpServletRequest req, @RequestParam("page") int page) {
        req.setAttribute("page", page);
        List<Book> books = bookService.paging(page);
        req.setAttribute("books", books);
        return "customerPage";
    }


    @PostMapping("/order")
    public String order(HttpServletRequest req, UsernamePasswordAuthenticationToken principal) {
        if (req.getParameter("bookToOrder") != null) {
            int bookToOrder = Integer.parseInt(req.getParameter("bookToOrder"));
            User user = (User) principal.getPrincipal();
            //пытаемся получить оформляющийся заказ
            Order orderFilled = orderService.findOrderFilledByUserId(user.getId());
            //если уже есть заказ со статусом "оформляется"
            if (orderFilled != null) {

                //если такая книга уже есть в заказе
                if (orderService.existBookInOrder(orderFilled.getId(), bookToOrder)) {
                    req.setAttribute("existBookInOrder", true);
                    return "customerPage";
                }
                //иначе добавляем в него книгу по bookId (обновляем заказ)
                orderService.addBookToOrder(orderFilled, bookToOrder);
                log.info("user {} added book {} to order {} at {}", user.getId(), bookToOrder,
                        orderFilled.getId(), LocalDateTime.now());
                //уменьшаем в каталоге кол-во этой книги на 1
                bookService.decrCountBook(bookToOrder, 1);
                req.setAttribute("existBookInOrder", true);
                String p = req.getParameter("page");
                return "redirect:/customerPage?page=" + p;
            }

            //иначе создаем новый заказ и добавляем в него книгу
            orderFilled = new Order();
            orderFilled.setUser(user);
            orderFilled.setOrderDate(LocalDate.now());
            orderFilled.setReturnDate(LocalDate.now().plusDays(30));
            orderFilled.setOrderStatus(OrderStatus.FILLED);
            orderService.save(orderFilled);
            Order order = orderService.findOrderFilledByUserId(user.getId());
            log.info("user {} created order {} at {}", user.getId(), order.getId(), LocalDateTime.now());
            //добавляем книгу в новый заказ
            orderService.addBookToOrder(order, bookToOrder);
            //уменьшаем в каталоге кол-во этой книги на 1
            bookService.decrCountBook(bookToOrder, 1);
            String p = req.getParameter("page");
            return "redirect:/customerPage?page=" + p;
        }
        String p = req.getParameter("page");
        return "redirect:/customerPage?page=" + p;
    }
}

