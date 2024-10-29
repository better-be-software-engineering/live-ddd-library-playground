package com.betterbeeng.domain.model.book;

public enum BookStatus {
	AVAILABLE, BORROWED, LOST, DESTROYED;

	public boolean canMoveTo(BookStatus status) {
		return switch (status) {
			case AVAILABLE -> this == BORROWED || this == LOST;
			case BORROWED -> this == AVAILABLE;
			case LOST -> this == BORROWED;
			case DESTROYED -> this == BORROWED;
		};
	}
}
