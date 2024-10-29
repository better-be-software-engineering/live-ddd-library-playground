package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.common.StringId;
import java.util.UUID;

public class BookId extends StringId {

	private BookId(UUID uuid) {
		super(uuid.toString());
	}

	static BookId generate() {
		return new BookId(UUID.randomUUID());
	}
}
