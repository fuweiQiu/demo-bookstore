package demo.controller;


import demo.util.http.ResponseEntityBuilder;
import demo.util.json.object.ObjectData;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import demo.databaseconfig.entity.Book;
import demo.databaseconfig.dao.BookDAO;

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

        return ResponseEntityBuilder.ok()
                .message()
                .data(bookData)
                .build();
    }


}
