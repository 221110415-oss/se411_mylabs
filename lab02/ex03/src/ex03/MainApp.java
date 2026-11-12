package ex03;

import ex03.data.Pipeline;

public class MainApp {

	public static void main(String[] args) {
		Pipeline<String, Integer> pipeline = Pipeline.<String>start()
				.add(s -> s.trim())          // String -> String (same type)
				.add(s -> s.toUpperCase())   // String -> String (same type)
				.add(s -> s.length());       // String -> Integer (type change)
		
		System.out.println(pipeline.execute("  hello generics  "));
		
		Pipeline<String, String> describe = pipeline.add(n -> "length is " + n); // Integer -> String
		System.out.println(describe.execute("  hello generics  "));
	}

}
