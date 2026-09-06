package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Stack Class Unit Tests")
public class StackTest {

	private Stack<String> stack;

	@BeforeEach
	public void init() {
		stack = new Stack<>();
	}

	@Nested
	@DisplayName("Constructor Tests")
	class ConstructorTests {

		@Test
		@DisplayName("Default constructor creates empty stack with default capacity")
		public void testDefaultConstructor() {
			Stack<Integer> s = new Stack<>();
			assertNotNull(s, "Stack should be created");
			assertThrows(NoSuchElementException.class, () -> s.pop());
		}

		@Test
		@DisplayName("Constructor with positive capacity initializes correctly")
		public void testConstructorWithPositiveCapacity() {
			Stack<String> s = new Stack<>(20);
			assertNotNull(s, "Stack with capacity 20 should be created");
			assertThrows(NoSuchElementException.class, () -> s.pop());
		}

		@Test
		@DisplayName("Constructor with zero capacity defaults to 10")
		public void testConstructorWithZeroCapacity() {
			Stack<String> s = new Stack<>(0);
			assertNotNull(s, "Stack with zero capacity should default to 10");
			s.push("test");
			assertEquals("test", s.pop());
		}

		@Test
		@DisplayName("Constructor with negative capacity defaults to 10")
		public void testConstructorWithNegativeCapacity() {
			Stack<String> s = new Stack<>(-5);
			assertNotNull(s, "Stack with negative capacity should default to 10");
			s.push("test");
			assertEquals("test", s.pop());
		}

		@Test
		@DisplayName("Constructor with large positive capacity")
		public void testConstructorWithLargeCapacity() {
			Stack<String> s = new Stack<>(1000);
			assertNotNull(s, "Stack with large capacity should be created");
			s.push("element");
			assertEquals("element", s.pop());
		}
	}

	@Nested
	@DisplayName("Push Method - Normal Behavior")
	class PushNormalBehaviorTests {

		@Test
		@DisplayName("Push single string element")
		public void testPushSingleElement() {
			stack.push("hello");
			String result = stack.pop();
			assertEquals("hello", result);
		}

		@Test
		@DisplayName("Push multiple elements maintains LIFO order")
		public void testPushMultipleElements() {
			stack.push("first");
			stack.push("second");
			stack.push("third");

			assertEquals("third", stack.pop());
			assertEquals("second", stack.pop());
			assertEquals("first", stack.pop());
		}

		@Test
		@DisplayName("Push various string values")
		public void testPushVariousStrings() {
			stack.push("empty string");
			stack.push("spaces   in  middle");
			stack.push("123");
			stack.push("special!@#$%");

			assertEquals("special!@#$%", stack.pop());
			assertEquals("123", stack.pop());
			assertEquals("spaces   in  middle", stack.pop());
			assertEquals("empty string", stack.pop());
		}

		@Test
		@DisplayName("Push with generic type - Integer")
		public void testPushWithIntegerType() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(10);
			intStack.push(20);
			intStack.push(30);

			assertEquals(30, intStack.pop());
			assertEquals(20, intStack.pop());
			assertEquals(10, intStack.pop());
		}

		@Test
		@DisplayName("Push with generic type - Double")
		public void testPushWithDoubleType() {
			Stack<Double> doubleStack = new Stack<>();
			doubleStack.push(3.14);
			doubleStack.push(2.71);
			doubleStack.push(1.41);

			assertEquals(1.41, doubleStack.pop());
			assertEquals(2.71, doubleStack.pop());
			assertEquals(3.14, doubleStack.pop());
		}

		@Test
		@DisplayName("Push same value multiple times")
		public void testPushDuplicateValues() {
			stack.push("same");
			stack.push("same");
			stack.push("same");

			assertEquals("same", stack.pop());
			assertEquals("same", stack.pop());
			assertEquals("same", stack.pop());
		}

		@Test
		@DisplayName("Push interleaved with pop operations")
		public void testPushPopInterleaved() {
			stack.push("1");
			stack.push("2");
			assertEquals("2", stack.pop());
			stack.push("3");
			assertEquals("3", stack.pop());
			stack.push("4");
			stack.push("5");
			assertEquals("5", stack.pop());
			assertEquals("4", stack.pop());
			assertEquals("1", stack.pop());
		}
	}

	@Nested
	@DisplayName("Push Method - Edge Cases")
	class PushEdgeCasesTests {

		@Test
		@DisplayName("Push null value")
		public void testPushNull() {
			stack.push(null);
			assertNull(stack.pop(), "Stack should accept and return null");
		}

		@Test
		@DisplayName("Push multiple null values")
		public void testPushMultipleNulls() {
			stack.push(null);
			stack.push(null);
			stack.push("not null");

			assertEquals("not null", stack.pop());
			assertNull(stack.pop());
			assertNull(stack.pop());
		}

		@Test
		@DisplayName("Push empty string")
		public void testPushEmptyString() {
			stack.push("");
			assertEquals("", stack.pop());
		}

		@Test
		@DisplayName("Push string with only whitespace")
		public void testPushWhitespaceOnlyString() {
			stack.push("   ");
			stack.push("\t");
			stack.push("\n");

			assertEquals("\n", stack.pop());
			assertEquals("\t", stack.pop());
			assertEquals("   ", stack.pop());
		}

		@Test
		@DisplayName("Push very long string")
		public void testPushLongString() {
			String longString = "x".repeat(10000);
			stack.push(longString);
			assertEquals(longString, stack.pop());
		}

		@Test
		@DisplayName("Push many elements beyond initial capacity")
		public void testPushManyElements() {
			int count = 100;
			for (int i = 0; i < count; i++) {
				stack.push("element" + i);
			}
			// I think this is to verify LIFO
			for (int i = count - 1; i >= 0; i--) {
				assertEquals("element" + i, stack.pop());
			}
		}

		@Test
		@DisplayName("Push zero as integer")
		public void testPushZeroInteger() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(0);
			assertEquals(0, intStack.pop());
		}

		@Test
		@DisplayName("Push negative numbers")
		public void testPushNegativeNumbers() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(-10);
			intStack.push(-5);
			intStack.push(-1);

			assertEquals(-1, intStack.pop());
			assertEquals(-5, intStack.pop());
			assertEquals(-10, intStack.pop());
		}

		@Test
		@DisplayName("Push Integer.MIN_VALUE and Integer.MAX_VALUE")
		public void testPushMinMaxValues() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(Integer.MIN_VALUE);
			intStack.push(Integer.MAX_VALUE);

			assertEquals(Integer.MAX_VALUE, intStack.pop());
			assertEquals(Integer.MIN_VALUE, intStack.pop());
		}
	}

	@Nested
	@DisplayName("Pop Method - Normal Behavior")
	class PopNormalBehaviorTests {

		@Test
		@DisplayName("Pop single element")
		public void testPopSingleElement() {
			stack.push("value");
			String result = stack.pop();
			assertEquals("value", result);
		}

		@Test
		@DisplayName("Pop returns elements in LIFO order")
		public void testPopLIFOOrder() {
			stack.push("first");
			stack.push("second");
			stack.push("third");

			String third = stack.pop();
			String second = stack.pop();
			String first = stack.pop();

			assertEquals("third", third);
			assertEquals("second", second);
			assertEquals("first", first);
		}

		@Test
		@DisplayName("Pop from stack with null element")
		public void testPopNullElement() {
			stack.push(null);
			assertNull(stack.pop());
		}

		@Test
		@DisplayName("Pop empty string")
		public void testPopEmptyString() {
			stack.push("");
			assertEquals("", stack.pop());
		}

		@Test
		@DisplayName("Pop after multiple pushes and pops")
		public void testPopAfterInterleavedOperations() {
			stack.push("a");
			stack.push("b");
			stack.push("c");

			stack.pop();
			stack.pop();

			String result = stack.pop();
			assertEquals("a", result);
		}

		@Test
		@DisplayName("Pop with Integer stack")
		public void testPopIntegerStack() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(100);
			intStack.push(200);

			assertEquals(200, intStack.pop());
			assertEquals(100, intStack.pop());
		}
	}

	// i think the edge cases start here
	@Nested
	@DisplayName("Pop Method - Boundary & Edge Cases")
	class PopBoundaryEdgeCasesTests {

		@Test
		@DisplayName("Pop from empty stack throws NoSuchElementException")
		public void testPopFromEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> stack.pop(),
					"Popping from empty stack should throw NoSuchElementException");
		}

		@Test
		@DisplayName("Pop from empty stack has correct exception message")
		public void testPopFromEmptyStackExceptionMessage() {
			NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> stack.pop());

			String message = exception.getMessage();
			assertNotNull(message, "Exception message should not be null");
			assertTrue(message.contains("empty"), "Exception message should indicate stack is empty");
			assertTrue(message.contains("Stack"), "Exception message should mention 'Stack'");
		}

		@Test
		@DisplayName("Pop until stack is empty")
		public void testPopUntilEmpty() {
			stack.push("1");
			stack.push("2");
			stack.push("3");

			stack.pop();
			stack.pop();
			stack.pop();

			// this next pop should throw
			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Pop after push-pop cycle returns to empty state")
		public void testPopAfterPushPopCycle() {
			stack.push("test");
			stack.pop();

			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Pop from stack with one element leaves stack empty")
		public void testPopSingleElementLeavesEmpty() {
			stack.push("only");
			stack.pop();

			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Multiple consecutive pops from empty stack all throw")
		public void testMultipleConsecutivePopThrows() {
			assertThrows(NoSuchElementException.class, () -> stack.pop());
			assertThrows(NoSuchElementException.class, () -> stack.pop());
			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}
	}

	@Nested
	@DisplayName("State Management & Integration Tests")
	class StateManagementTests {

		@Test
		@DisplayName("State changes correctly across multiple push/pop operations")
		public void testStateChangesAcrossOperations() {
			assertThrows(NoSuchElementException.class, () -> stack.pop());

			stack.push("A");
			assertEquals("A", stack.pop());

			assertThrows(NoSuchElementException.class, () -> stack.pop());

			stack.push("B");
			stack.push("C");
			assertEquals("C", stack.pop());

			assertEquals("B", stack.pop());

			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Stack maintains correct state after large sequence of operations")
		public void testStateAfterLargeSequence() {
			int pushCount = 50;

			for (int i = 0; i < pushCount; i++) {
				stack.push("elem" + i);
			}

			for (int i = 0; i < pushCount / 2; i++) {
				stack.pop();
			}

			// Verify next pop is correct (should be element 24, since we pushed 0-49)
			assertEquals("elem" + (pushCount / 2 - 1), stack.pop());

			// Pop remaining elements in order
			for (int i = pushCount / 2 - 2; i >= 0; i--) {
				assertEquals("elem" + i, stack.pop());
			}

			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Push-pop-push-pop cycles maintain correct state")
		public void testPushPopCycles() {
			for (int cycle = 0; cycle < 5; cycle++) {
				stack.push("cycle" + cycle);
				assertEquals("cycle" + cycle, stack.pop());
				assertThrows(NoSuchElementException.class, () -> stack.pop());
			}
		}

		@Test
		@DisplayName("Mixed operations with different types and nulls")
		public void testMixedOperationsWithNulls() {
			stack.push("first");
			stack.push(null);
			stack.push("third");
			stack.push(null);

			assertNull(stack.pop());
			assertEquals("third", stack.pop());
			assertNull(stack.pop());
			assertEquals("first", stack.pop());

			assertThrows(NoSuchElementException.class, () -> stack.pop());
		}

		@Test
		@DisplayName("Stack returns exact references (not copies)")
		public void testStackReturnsExactReferences() {
			Stack<StringBuilder> sbStack = new Stack<>();
			StringBuilder sb = new StringBuilder("test");
			sbStack.push(sb);

			StringBuilder popped = sbStack.pop();
			assertSame(sb, popped, "Stack should return same reference");
		}

		@Test
		@DisplayName("Multiple independent stacks maintain separate state")
		public void testMultipleIndependentStacks() {
			Stack<String> stack1 = new Stack<>();
			Stack<String> stack2 = new Stack<>();

			stack1.push("stack1");
			stack2.push("stack2");

			assertEquals("stack1", stack1.pop());
			assertEquals("stack2", stack2.pop());

			assertThrows(NoSuchElementException.class, () -> stack1.pop());
			assertThrows(NoSuchElementException.class, () -> stack2.pop());
		}
	}

	@Nested
	@DisplayName("Branching Logic Tests")
	class BranchingLogicTests {

		@Test
		@DisplayName("Pop checks isEmpty condition - true branch (empty)")
		public void testPopIsEmptyTrueBranch() {
			// elements.isEmpty() returns true
			NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> stack.pop());
			assertNotNull(exception.getMessage());
		}

		@Test
		@DisplayName("Pop checks isEmpty condition - false branch (not empty)")
		public void testPopIsEmptyFalseBranch() {
			stack.push("element");
			String result = stack.pop();
			assertEquals("element", result);
		}

		@Test
		@DisplayName("Constructor capacity check - positive capacity branch")
		public void testConstructorCapacityCheckPositive() {
			Stack<String> s = new Stack<>(15);
			s.push("test");
			assertEquals("test", s.pop());
		}

		@Test
		@DisplayName("Constructor capacity check - non-positive capacity branch")
		public void testConstructorCapacityCheckNonPositive() {
			Stack<String> s1 = new Stack<>(0);
			Stack<String> s2 = new Stack<>(-10);

			s1.push("test1");
			s2.push("test2");

			assertEquals("test1", s1.pop());
			assertEquals("test2", s2.pop());
		}
	}

	@Nested
	@DisplayName("Generic Type Support Tests")
	class GenericTypeTests {

		@Test
		@DisplayName("Stack<String> operations")
		public void testStringStack() {
			Stack<String> strStack = new Stack<>();
			strStack.push("a");
			strStack.push("b");
			assertEquals("b", strStack.pop());
		}

		@Test
		@DisplayName("Stack<Integer> operations")
		public void testIntegerStack() {
			Stack<Integer> intStack = new Stack<>();
			intStack.push(1);
			intStack.push(2);
			assertEquals(2, intStack.pop());
		}

		@Test
		@DisplayName("Stack<Boolean> operations")
		public void testBooleanStack() {
			Stack<Boolean> boolStack = new Stack<>();
			boolStack.push(true);
			boolStack.push(false);
			assertEquals(false, boolStack.pop());
			assertEquals(true, boolStack.pop());
		}

		@Test
		@DisplayName("Stack<Object> with mixed object types")
		public void testObjectStack() {
			Stack<Object> objStack = new Stack<>();
			objStack.push("string");
			objStack.push(42);
			objStack.push(3.14);

			assertEquals(3.14, objStack.pop());
			assertEquals(42, objStack.pop());
			assertEquals("string", objStack.pop());
		}
	}
}