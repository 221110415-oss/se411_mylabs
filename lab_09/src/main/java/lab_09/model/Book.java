package lab_09.model;

public class Book extends Item {
	private String author;
	private String isbn;
	
	public Book(int id, String name, String author, String isbn) {
		super(id, name);
		setAuthor(author);
		setIsbn(isbn);
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		if (author == null || author.isEmpty()) {
			throw new IllegalArgumentException("Author cannot be null or empty");
		}
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("Isbn cannot be null or empty");
		}
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + getId() + ", name=" + getName() + ", author=" + author + ", isbn=" + isbn + "]";
	}
}
