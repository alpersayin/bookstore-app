package com.alpersayin.getir.mapper;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.payload.request.BookRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class BookMapper {
    public abstract BookEntity mapToBook(BookRequest bookRequest);
}
