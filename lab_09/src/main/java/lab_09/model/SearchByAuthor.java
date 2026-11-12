package lab_09.model;

public class SearchByAuthor implements SearchStrategy<Book> {
	private String author;

	public SearchByAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean matches(Book item) {
		return item.getAuthor().equals(author);
	}
}
