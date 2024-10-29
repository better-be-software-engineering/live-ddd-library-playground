package com.betterbeeng.application.task.data;

import com.betterbeeng.application.task.TaskData;
import com.betterbeeng.domain.model.book.BookId;
import com.betterbeeng.domain.model.book.BorrowingId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerifyBorrowingWasCompletedData implements TaskData {
	private final BookId bookId;
	private final BorrowingId borrowingId;
}
