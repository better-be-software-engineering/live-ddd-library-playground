package com.betterbeeng.application.service;

import com.betterbeeng.application.exception.BookNotFoundException;
import com.betterbeeng.application.exception.UserNotFoundException;
import com.betterbeeng.application.task.TaskScheduler;
import com.betterbeeng.application.task.TaskType;
import com.betterbeeng.application.task.data.VerifyBorrowingWasCompletedData;
import com.betterbeeng.domain.model.book.Book;
import com.betterbeeng.domain.model.book.BookId;
import com.betterbeeng.domain.model.book.BookRepository;
import com.betterbeeng.domain.model.book.Borrowing;
import com.betterbeeng.domain.model.user.User;
import com.betterbeeng.domain.model.user.UserId;
import com.betterbeeng.domain.model.user.UserManager;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.transaction.PlatformTransactionManager;

public class BorrowBook extends ApplicationService {
	private final TaskScheduler taskScheduler;
	private final BookRepository bookRepository;
	private final UserManager userManager;

	protected BorrowBook(PlatformTransactionManager platformTransactionManager, TaskScheduler taskScheduler,
	                     BookRepository bookRepository, UserManager userManager) {
		super(platformTransactionManager);
		this.taskScheduler = taskScheduler;
		this.bookRepository = bookRepository;
		this.userManager = userManager;
	}

	public <T> T borrow(BookId bookId, UserId userId, Function<Borrowing, T> mapper) {
		Optional<User> userOpt = userManager.getUser(userId);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException(userId);
		}
		User user = userOpt.get();
		return executeInTransactionAndReturn(() -> {
			Optional<Book> bookOpt = bookRepository.findById(bookId);
			if (bookOpt.isEmpty()) {
				throw new BookNotFoundException(bookId);
			}
			Book book = bookOpt.get();
			Borrowing borrowing = book.borrow(user);
			bookRepository.save(book);
			taskScheduler.schedule(TaskType.VERIFY_BORROWING_WAS_COMPLETED,
					new VerifyBorrowingWasCompletedData(bookOpt.get().getId(), borrowing.getId()),
					borrowing.getReturnedDueAt());
			return mapper.apply(borrowing);
		});
	}
}
