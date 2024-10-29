package com.betterbeeng.domain.model.book;

import com.betterbeeng.domain.model.author.AuthorId;
import java.util.List;
import java.util.Optional;
import lombok.Value;

public interface BookRepository {

	List<Book> findBy(BookQuery bookQuery);

	void save(Book book);

	Optional<Book> findById(BookId bookId);

	@Value
	class BookQuery {
		BookName name;
		AuthorId authorId;
		ISBN isbn;

		public BookQuery(BookName name, AuthorId authorId, ISBN isbn) {
			if (name == null && authorId == null && isbn == null) {
				throw new IllegalArgumentException("At least one parameter should be provided");
			}
			this.name = name;
			this.authorId = authorId;
			this.isbn = isbn;
		}

		public Optional<BookName> getName() {
			return Optional.ofNullable(name);
		}

		public Optional<AuthorId> getAuthorId() {
			return Optional.ofNullable(authorId);
		}

		public Optional<ISBN> getIsbn() {
			return Optional.ofNullable(isbn);
		}
	}
}
