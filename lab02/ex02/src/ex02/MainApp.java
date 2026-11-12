
package ex02;

import java.util.ArrayList;
import java.util.List;

import ex02.data.NumberBox;

public class MainApp {

	public static void main(String[] args) {
		NumberBox<Integer> intBox = new NumberBox<>();
		intBox.setItem(10);
		System.out.println("Integer box item: " + intBox.getItem());
		System.out.println("Integer box add 5: " + intBox.add(5));

		NumberBox<Double> doubleBox = new NumberBox<>();
		doubleBox.setItem(2.5);
		System.out.println("Double box item: " + doubleBox.getItem());
		System.out.println("Double box add 0.25: " + doubleBox.add(0.25));

		List<NumberBox<?>> boxes = new ArrayList<>();
		boxes.add(intBox);
		boxes.add(doubleBox);
		System.out.println("Sum of all boxes: " + NumberBox.sum(boxes));
	}

}
