package com.alpersayin.getir.controller;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.payload.request.BookRequest;
import com.alpersayin.getir.payload.response.ApiResponse;
import com.alpersayin.getir.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create-book")
    public ResponseEntity<ApiResponse<BookEntity>> createBook(@RequestBody BookRequest bookRequest) {
        ApiResponse<BookEntity> response = new ApiResponse<>();
        response.setData(bookService.createBook(bookRequest));
        response.setMessage("New book created.");
        return new ResponseEntity<>(response, CREATED);
    }

    @PatchMapping("/{id}/increase/{number}")
    public ResponseEntity<ApiResponse<BookEntity>> increaseBookStock(@PathVariable String id, @PathVariable @Min(1) @Max(50) Integer number) {
        ApiResponse<BookEntity> response = new ApiResponse<>();
        response.setData(bookService.increaseStock(id, number));
        response.setMessage("Stock increased.");
        return new ResponseEntity<>(response, OK);
    }

    @PatchMapping("/{id}/decrease/{number}")
    public ResponseEntity<ApiResponse<BookEntity>> decreaseBookStock(@PathVariable String id, @PathVariable @Min(1) @Max(50) Integer number) {
        ApiResponse<BookEntity> response = new ApiResponse<>();
        response.setData(bookService.decreaseStock(id, number));
        response.setMessage("Stock decreased.");
        return new ResponseEntity<>(response, OK);
    }

}
