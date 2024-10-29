package com.betterbeeng.domain.model.author;

import com.betterbeeng.domain.model.common.StringId;
import java.util.UUID;

public class AuthorId extends StringId {

	private AuthorId(UUID uuid) {
		super(uuid.toString());
	}

	public static AuthorId generate() {
		return new AuthorId(UUID.randomUUID());
	}
}
