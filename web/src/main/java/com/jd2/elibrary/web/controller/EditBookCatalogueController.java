package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class EditBookCatalogueController {
    private static final Logger log = LoggerFactory.getLogger(EditBookCatalogueController.class);

    private final BookService bookService;

    public EditBookCatalogueController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/editBookCatalogue")
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
        return "editBookCatalogue";
    }

    @PostMapping("/editBookCatalogue")
    public String page(HttpServletRequest req, @RequestParam("page") int page) {
        req.setAttribute("page", page);
        List<Book> books = bookService.paging(page);
        req.setAttribute("books", books);
        return "editBookCatalogue";
    }

    @PostMapping("/bookDelete")
    public String bookDelete(HttpServletRequest req) {
        String p = req.getParameter("page");
        int bookDelete = Integer.parseInt(req.getParameter("bookDelete"));
        int countDelete = Integer.parseInt(req.getParameter("countDelete"));
        if (bookService.findById(bookDelete).getCount() < countDelete) {
            req.setAttribute("error", true);
            return "editBookCatalogue";
        }
        bookService.decrCountBook(bookDelete, countDelete);
        log.info("book {} decreased by {}", bookDelete, countDelete);
        return "redirect:/editBookCatalogue?page=" + p;
    }


    @PostMapping("/bookAdd")
    public String bookAdd(HttpServletRequest req) {
        String p = req.getParameter("page");
        if (req.getParameter("bookAdd") != null) {
            int bookAdd = Integer.parseInt(req.getParameter("bookAdd"));
            int countAdd = Integer.parseInt(req.getParameter("countAdd"));
            bookService.incrCountBook(bookAdd, countAdd);
            log.info("book {} increased by {}", bookAdd, countAdd);
        }
        return "redirect:/editBookCatalogue?page=" + p;
    }
}


