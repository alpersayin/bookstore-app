package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.exception.ApiNotFoundException;
import com.alpersayin.getir.mapper.BookMapper;
import com.alpersayin.getir.payload.request.BookRequest;
import com.alpersayin.getir.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final MongoTemplate mongoTemplate;

    @Override
    public BookEntity findByBookId(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Book Id with: " + id + " not found"));
    }

    @Override
    @Transactional
    public BookEntity createBook(BookRequest bookRequest) {
        return bookRepository.save(bookMapper.mapToBook(bookRequest));
    }

    @Override
    @Transactional
    public BookEntity increaseStock(String id, Integer number) {
        // ToDo: update islemi guncellenecek, repository icine update query yazilacak yada MongoTemplate updateFirst kullanilacak
        BookEntity book = findByBookId(id);
        Integer updatedStock = book.getStock() + number;
        book.setStock(updatedStock);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public BookEntity decreaseStock(String id, Integer number) {
        // ToDo: update islemi guncellenecek, repository icine update query yazilacak yada MongoTemplate updateFirst kullanilacak
        BookEntity book = findByBookId(id);
        int updatedStock;
        if (book.getStock() <= number) {
            updatedStock = 0;
        } else {
            updatedStock = book.getStock() - number;
        }
        book.setStock(updatedStock);
        return bookRepository.save(book);
    }

}
