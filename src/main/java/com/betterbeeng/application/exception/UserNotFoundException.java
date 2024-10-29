package com.betterbeeng.application.exception;

import com.betterbeeng.domain.model.user.UserId;
import lombok.Value;

@Value
public class UserNotFoundException extends RuntimeException {
	UserId userId;
}
