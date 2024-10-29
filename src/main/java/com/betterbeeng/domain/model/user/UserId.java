package com.betterbeeng.domain.model.user;

import com.betterbeeng.domain.model.common.StringId;
import java.util.UUID;

public class UserId extends StringId {
	private UserId(UUID id) {
		super(id.toString());
	}

	public static UserId generate() {
		return new UserId(UUID.randomUUID());
	}
}
