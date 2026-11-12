package lab_09.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
	private List<T> items = new ArrayList<T>();

	public void add(T item) {
		items.add(item);
	}
	
	public boolean remove(T item) {
		return items.remove(item);
	}
	
	public List<T> getAll() {
		return new ArrayList<T>(items);
	}
	
	public List<T> findItems(SearchStrategy<T> strategy) {
		List<T> result = new ArrayList<T>();
		for (T item : items) {
			if (strategy.matches(item)) {
				result.add(item);
			}
		}
		return result;
	}
}
