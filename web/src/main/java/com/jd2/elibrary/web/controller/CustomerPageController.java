package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private int pageNumber = 0;

    @GetMapping("/customerPage")
    public String doGet(HttpServletRequest req) {
        int page = 0;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Book> books = bookService.paging(page);
        int maxNumber = bookService.countPageBooks() - 1;
        req.setAttribute("books", books);
        req.setAttribute("maxNumber", maxNumber);
        req.setAttribute("page", page);
        return "customerPage";
    }

    @PostMapping("/customerPage")
    public String page(HttpServletRequest req, @RequestParam("page") int page) {
        req.setAttribute("page", page);
        List<Book> books = bookService.paging(page);
        req.setAttribute("books", books);
        return "customerPage";
    }


//    @PostMapping(("/customerPage"))
//    public String order(HttpServletRequest req) {
//        if (req.getParameter("bookToOrder") != null) {
//            int bookToOrder = Integer.parseInt(req.getParameter("bookToOrder"));
//            int count = bookService.findById(bookToOrder).getCount();
//            User user = (User) req.getSession().getAttribute("login");
//            //пытаемся получить оформляющийся заказ
//            Order orderFilled = orderService.findOrderFilledByUserId(user.getId());
//
//            //если книга имеется в наличии
//            if (count > 0) {
//                //если уже есть заказ со статусом "оформляется"
//                if (orderFilled != null) {
//                    //если такая книга уже есть в заказе
//                    if (orderService.existBookInOrder(orderFilled.getId(), bookToOrder)) {
//                        //вывести сообщение о наличии книги в заказе
//                        return "customerPage";
//                    }
//                    //иначе добавляем в него книгу по bookId (обновляем заказ)
//                    orderService.addBookToOrder(orderFilled, bookToOrder);
//                    log.info("user {} added book {} to order {} at {}", user.getId(), bookToOrder,
//                            orderFilled.getId(), LocalDateTime.now());
//                    //уменьшаем в каталоге кол-во этой книги на 1
//                    bookService.decrCountBook(bookToOrder, 1);
//                    return "redirect:/customerPage";
//                }
//            }
//            //иначе создаем новый заказ и добавляем в него книгу
//            orderFilled = new Order();
//            orderFilled.setUser(user);
//            orderFilled.setOrderDate(LocalDate.now());
//            orderFilled.setReturnDate(LocalDate.now().plusDays(30));
//            orderFilled.setOrderStatus(OrderStatus.FILLED);
//            orderService.save(orderFilled);
//            Order order = orderService.findOrderFilledByUserId(user.getId());
//            log.info("user {} created order {} at {}", user.getId(), order.getId(), LocalDateTime.now());
//            //добавляем книгу в новый заказ
//            orderService.addBookToOrder(order, bookToOrder);
//            //уменьшаем в каталоге кол-во этой книги на 1
//            bookService.decrCountBook(bookToOrder, 1);
//            return "redirect:/customerPage";
//        }
//        //вывести сообщение о недоступности книги для заказа
//        return "redirect:/customerPage";
//    }
}

