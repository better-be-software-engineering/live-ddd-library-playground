package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.common.RequiredValue;
import lombok.NonNull;

public class ISBN extends RequiredValue<String> {
	private ISBN(@NonNull String value) {
		super(value);
		validateValue();
	}

	public static ISBN of(String value) {
		return new ISBN(value);
	}

	private void validateValue() {
		if (getValue().length() != 13) {
			throw new IllegalArgumentException("ISBN should be 13 characters long");
		}
	}
}
