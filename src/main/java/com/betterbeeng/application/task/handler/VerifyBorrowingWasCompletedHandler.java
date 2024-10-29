package com.betterbeeng.application.task.handler;

import com.betterbeeng.application.exception.BookNotFoundException;
import com.betterbeeng.application.service.ApplicationService;
import com.betterbeeng.application.task.TaskHandler;
import com.betterbeeng.application.task.TaskType;
import com.betterbeeng.application.task.data.VerifyBorrowingWasCompletedData;
import com.betterbeeng.domain.model.book.Book;
import com.betterbeeng.domain.model.book.BookRepository;
import com.betterbeeng.domain.model.book.Borrowing;
import com.betterbeeng.domain.model.user.UserManager;
import java.util.Optional;
import org.springframework.transaction.PlatformTransactionManager;

public class VerifyBorrowingWasCompletedHandler extends ApplicationService implements TaskHandler<VerifyBorrowingWasCompletedData> {
	private final UserManager userManager;
	private final BookRepository bookRepository;

	public VerifyBorrowingWasCompletedHandler(PlatformTransactionManager platformTransactionManager,
	                                          UserManager userManager, BookRepository bookRepository) {
		super(platformTransactionManager);
		this.userManager = userManager;
		this.bookRepository = bookRepository;
	}

	@Override
	public boolean handles(TaskType<VerifyBorrowingWasCompletedData> taskType) {
		return TaskType.VERIFY_BORROWING_WAS_COMPLETED.equals(taskType);
	}

	@Override
	public void handle(VerifyBorrowingWasCompletedData taskData) {
		executeInTransaction(() -> {
			Optional<Book> bookOpt = bookRepository.findById(taskData.getBookId());
			if (bookOpt.isEmpty()) {
				throw new BookNotFoundException(taskData.getBookId());
			}
			Book book = bookOpt.get();
			Borrowing borrowing = book.getBorrowingOrThrow(taskData.getBorrowingId());
			if (borrowing.isOverdue()) {
				userManager.banUser(borrowing.getUserId());
				book.declareLost(taskData.getBorrowingId());
				bookRepository.save(book);
			}
		});
	}
}
