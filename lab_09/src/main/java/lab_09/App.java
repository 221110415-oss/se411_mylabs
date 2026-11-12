package lab_09;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab_09.model.Book;
import lab_09.model.ECategory;
import lab_09.model.ElectronicDevice;
import lab_09.model.Inventory;
import lab_09.model.Item;
import lab_09.model.SearchByAuthor;
import lab_09.model.SearchByCategory;
import lab_09.model.SearchById;
import lab_09.model.SearchByName;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		Inventory<Item> inventory = new Inventory<Item>();
		inventory.add(new Book(1, "Concurrency in Java", "Author One", "111-1111111111"));
		inventory.add(new Book(2, "Effective Java", "Author Two", "222-2222222222"));
		inventory.add(new ElectronicDevice(3, "Galaxy S", "Samsung", ECategory.SMARTPHONE));
		inventory.add(new ElectronicDevice(4, "ThinkPad", "Lenovo", ECategory.LAPTOP));
		
		displayInventory(inventory);
		
		//search by name (strategy defined for Item)
		System.out.println("Search by name: " + inventory.findItems(new SearchByName("Concurrency in Java")));
		System.out.println("Search by name: " + inventory.findItems(new SearchByName("ThinkPad")));
		System.out.println("Search by id: " + inventory.findItems(new SearchById(2)));
		
		//lambda expression as a SearchStrategy
		List<Item> found = inventory.findItems(item -> item.getName().startsWith("E"));
		System.out.println("Search with a lambda: " + found);
		
		//strategies specific to Book and ElectronicDevice
		Inventory<Book> books = new Inventory<Book>();
		books.add(new Book(1, "Concurrency in Java", "Author One", "111-1111111111"));
		books.add(new Book(2, "Effective Java", "Author Two", "222-2222222222"));
		System.out.println("Search by author: " + books.findItems(new SearchByAuthor("Author Two")));
		
		Inventory<ElectronicDevice> devices = new Inventory<ElectronicDevice>();
		devices.add(new ElectronicDevice(3, "Galaxy S", "Samsung", ECategory.SMARTPHONE));
		devices.add(new ElectronicDevice(4, "ThinkPad", "Lenovo", ECategory.LAPTOP));
		System.out.println("Search by category: " + devices.findItems(new SearchByCategory(ECategory.LAPTOP)));
		
		displayInventory(books);
		
		logger.info("Application is closing...");
	}

	public static void displayInventory(Inventory<?> inventory) {
		System.out.println("--- Inventory ---");
		for (Object item : inventory.getAll()) {
			System.out.println(item);
		}
	}

}
