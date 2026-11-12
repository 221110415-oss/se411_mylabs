package ex04;

import java.util.Arrays;
import java.util.List;

public class MainApp {

	public static void printList(List<?> list) {
		for(Object o : list) {
			System.out.println(o);
		}
	}
	
	public static double sumNumbers(List<? extends Number> list) {
		double sum = 0;
		for(Number n : list) {
			sum += n.doubleValue();
		}
		return sum;
	}

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("a", "b", "c");
		List<Integer> ints = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(1.5, 2.5);
		
		printList(strings);
		printList(ints);
		
		System.out.println("Sum of ints: " + sumNumbers(ints));
		System.out.println("Sum of doubles: " + sumNumbers(doubles));
	}

}
