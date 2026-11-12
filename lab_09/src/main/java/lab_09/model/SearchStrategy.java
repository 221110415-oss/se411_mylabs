package lab_09.model;

@FunctionalInterface
public interface SearchStrategy<T> {
	public boolean matches(T item);
}
