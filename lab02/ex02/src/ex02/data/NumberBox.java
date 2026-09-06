package ex02.data;

import java.util.List;


public class NumberBox<T extends Number> {

	private T element;
	
	public T getElement() {
		return element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}

	
	public Double add(T other) {
		return (element.doubleValue() + other.doubleValue());
	}
	
	public static Double sum(List<NumberBox<?>> list) throws IllegalArgumentException {
		if(list == null) throw new IllegalArgumentException("Cannot sum null list");
		double sum = 0;
		try {
		for(NumberBox n : list) {
			sum += n.getElement().doubleValue();
		}
		} catch (Exception e) {
			throw e;
		}
		return sum;
	}
}
