package ex01;

import ex01.data.PrintableList;

public class MainApp {

	public static void main(String[] args) {
		PrintableList<String> p = new PrintableList("first string", "second string", "third string");
		p.print();
	}

}
