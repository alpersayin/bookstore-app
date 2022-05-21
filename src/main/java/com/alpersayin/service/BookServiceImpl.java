package com.alpersayin.service;

import com.alpersayin.exception.ApiNotFoundException;
import com.alpersayin.entity.BookEntity;
import com.alpersayin.mapper.BookMapper;
import com.alpersayin.payload.request.BookRequest;
import com.alpersayin.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public BookEntity stockIncrease(String id, Integer number) {
        BookEntity book = findByBookId(id);
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("stock", number + book.getStock());
        mongoTemplate.updateFirst(query, update, BookEntity.class);
        return findByBookId(id);
    }

    @Override
    @Transactional
    public BookEntity stockDecrease(String id, Integer number) {
        BookEntity book = findByBookId(id);
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update();
        int updatedStock;
        if (book.getStock() <= number) {
            updatedStock = 0;
        } else {
            updatedStock = book.getStock() - number;
        }
        update.set("stock", updatedStock);
        mongoTemplate.updateFirst(query, update, BookEntity.class);
        return findByBookId(id);
    }

}
