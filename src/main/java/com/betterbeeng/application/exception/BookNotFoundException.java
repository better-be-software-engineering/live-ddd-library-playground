package com.betterbeeng.application.exception;

import com.betterbeeng.domain.model.book.BookId;
import lombok.Value;

@Value
public class BookNotFoundException extends RuntimeException {
	BookId bookId;
}
