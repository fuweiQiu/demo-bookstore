package demo.controller;


import demo.util.http.ResponseEntityBuilder;
import demo.util.json.object.ObjectData;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import demo.databaseconfig.entity.Book;
import demo.databaseconfig.dao.BookDAO;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookDAO bookDAO;

    @GetMapping(path = "/book")
    public ResponseEntity<?> getBook(@RequestParam(required = false) Integer id) {
        if(id == null){
            Book[] allbook = bookDAO.findAll();
            return ResponseEntityBuilder.ok()
                    .message("")
                    .data(allbook)
                    .build();
        }

        Book book = bookDAO.findById(id).get();


        ObjectData bookData = new ObjectData()
                .add("id", book.getId())
                .add("name", book.getName())
                .add("publicationDate",  book.getPublicationDate())
                .add("info", book.getInfo());

        return ResponseEntityBuilder.success()
                .message()
                .data(bookData)
                .build();
    }

    @PostMapping(path = "/book")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        book.setCreateTime(LocalDate.now());
        bookDAO.save(book);
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .data()
                .build();
    }

    @PatchMapping(path = "/book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Book thatBook = bookDAO.findById(id).get();
        thatBook.setName(book.getName());
        thatBook.setAuthorId(book.getAuthorId());
        thatBook.setPublicationDate(book.getPublicationDate());
        thatBook.setInfo(book.getInfo());
        bookDAO.save(thatBook);
        retrun ResponseEntityBuilder.success()
                .message("更新成功")
                .data()
                .build();
    }

    @DeleteMapping(path = "/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        bookDAO.deleteById(id);
        return ResponseEntityBuilder.success()
                .message("刪除成功")
                .data()
                .build();
    }



}
