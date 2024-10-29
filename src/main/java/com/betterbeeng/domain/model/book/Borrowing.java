package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.user.UserId;
import java.time.Clock;
import java.time.Instant;
import lombok.Getter;

@Getter
public class Borrowing {
	private final BorrowingId id;
	private final BookId bookId;
	private final UserId userId;
	private final Instant borrowedAt;
	private final Instant returnedDueAt;
	private Instant returnedAt;

	public Borrowing(BookId bookId, UserId userId) {
		this.id = BorrowingId.generate();
		this.bookId = bookId;
		this.userId = userId;
		this.borrowedAt = Instant.now(Clock.systemUTC());
		this.returnedDueAt = borrowedAt.plusSeconds(60 * 60 * 24 * 60);
	}

	public void returnBook() {
		returnedAt = Instant.now(Clock.systemUTC());
	}

	public boolean isOverdue() {
		return wasReturned() && Instant.now(Clock.systemUTC()).isAfter(returnedDueAt);
	}

	public boolean wasReturned() {
		return returnedAt == null;
	}
}
