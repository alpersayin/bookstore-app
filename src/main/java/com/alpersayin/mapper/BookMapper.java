package com.alpersayin.mapper;

import com.alpersayin.entity.BookEntity;
import com.alpersayin.payload.request.BookRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class BookMapper {
    public abstract BookEntity mapToBook(BookRequest bookRequest);
}
