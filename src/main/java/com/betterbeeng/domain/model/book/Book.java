package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.author.AuthorId;
import com.betterbeeng.domain.model.user.User;
import com.betterbeeng.domain.model.user.UserStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Book {
	private final BookId id;
	private final BookName bookName;
	private final ISBN isbn;
	private final AuthorId authorId;
	private final List<Borrowing> borrowings;
	private BookStatus status;

	public Book(BookName bookName, ISBN isbn, AuthorId authorId) {
		this.id = BookId.generate();
		this.bookName = bookName;
		this.isbn = isbn;
		this.authorId = authorId;
		this.status = BookStatus.AVAILABLE;
		this.borrowings = new ArrayList<>();
	}

	public Borrowing borrow(User user) {
		if (user.getStatus() == UserStatus.BANNED) {
			throw new IllegalArgumentException("User " + user.getId() + " is banned.");
		}
		if (status != BookStatus.AVAILABLE) {
			throw new IllegalArgumentException("Book " + id + " is not available for borrowing.");
		}
		Borrowing borrowing = new Borrowing(id, user.getId());
		borrowings.add(borrowing);
		updateStatus(BookStatus.BORROWED);
		return borrowing;
	}

	public void returnBook(BorrowingId borrowingId) {
		Borrowing borrowing = getBorrowingOrThrow(borrowingId);
		borrowing.returnBook();
		updateStatus(BookStatus.AVAILABLE);
	}

	public void declareLost(BorrowingId borrowingId) {
		getBorrowingOrThrow(borrowingId);
		updateStatus(BookStatus.LOST);
	}

	public void declareDestroyed(BorrowingId borrowingId) {
		getBorrowingOrThrow(borrowingId);
		updateStatus(BookStatus.DESTROYED);
	}

	private Borrowing getBorrowingOrThrow(BorrowingId borrowingId) {
		return borrowings.stream()
				.filter(b -> b.getId().equals(borrowingId))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Borrowing " + borrowingId + " not found for book " + id + "."));
	}

	private void updateStatus(BookStatus status) {
		if (!this.status.canMoveTo(status)) {
			throw new IllegalArgumentException("Invalid status transition from " + this.status + " to " + status + " for book " + id + ".");
		}
		this.status = status;
	}
}
