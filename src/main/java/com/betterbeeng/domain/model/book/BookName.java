package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.common.RequiredValue;
import lombok.NonNull;

public class BookName extends RequiredValue<String> {

	private BookName(@NonNull String value) {
		super(value);
		validate();
	}

	public static BookName of(@NonNull String value) {
		return new BookName(value);
	}

	private void validate() {
		if (getValue().isEmpty() || getValue().length() > 255) {
			throw new IllegalArgumentException("'name' should be between 1 and 255 characters");
		}
	}
}
