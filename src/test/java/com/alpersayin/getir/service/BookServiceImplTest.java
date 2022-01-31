package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.mapper.BookMapper;
import com.alpersayin.getir.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private BookServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new BookServiceImpl(bookRepository, bookMapper, mongoTemplate);
    }

    @Test
    void canfindByBookId() {
        // when
        BookEntity book = new BookEntity(
                "title name",
                "author name",
                "publisher name",
                10.0,
                120
        );
        BookEntity insertedBook = bookRepository.save(book);
        // then
        verify(underTest).findByBookId(insertedBook.getId());
    }

    @Test
    @Disabled
    void createBook() {
    }

    @Test
    @Disabled
    void stockIncrease() {
    }

    @Test
    @Disabled
    void stockDecrease() {
    }
}