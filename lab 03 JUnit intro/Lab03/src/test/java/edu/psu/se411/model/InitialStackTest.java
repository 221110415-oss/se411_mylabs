package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InitialStackTest {

	private Stack<String> s;
	
	@BeforeEach
	public void init() {
		s =  new Stack<>();
	}
	
	@Test
	public void someTest() {
		s.push("string 1");
		s.push("string 2");
		s.push("string 3");
		String str = s.pop();
		assertEquals(str, "string 2");
	}
	@Test
	public void pop_empty_stack() {
		NoSuchElementException thrown = 
				assertThrows(NoSuchElementException.class,
		      () -> s.pop(),
		      "Expected pop from empty Stack to throw, but it didn't"
		);

        assertTrue(thrown.getMessage().equals("Stack is empty,can’t pop"));

}
}
