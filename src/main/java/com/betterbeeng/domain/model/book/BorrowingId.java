package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.common.StringId;
import java.util.UUID;

public class BorrowingId extends StringId {
	private BorrowingId(UUID id) {
		super(id.toString());
	}

	public static BorrowingId generate() {
		return new BorrowingId(UUID.randomUUID());
	}
}
