## Instructions

We need to build the IT system for a library. 
- The library has a catalog of books.
- Books can be searched by name, author, or ISBN.
- The library has a catalog of users, and the users can borrow books from the library. 
- A book can be in several states: available, borrowed, lost, or destroyed. 
- A user can be in several states: active, or banned. 
- A book when borrowed must be returned by a certain date. A late return, or no return will force us to ban the user from our library. When late, the book is marked as lost.
- If the user returns the book after the return date, the book will be reverted back to available.
- If the book is returned in a destroyed state, the book will be marked as destroyed.



## Requirements

- We will have to this list of books in the database
- Books must have an id, a name, an author, an ISBN and a status.
- We will have to store the list of authors in the database
- We will have to store the list of users in the database
- Users must have an id, a name, and a status.
- We wil have to store the list of ongoing borrowings in the database.