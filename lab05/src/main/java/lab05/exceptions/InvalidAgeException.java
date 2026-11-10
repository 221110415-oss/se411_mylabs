package lab05.exceptions;

import lab05.Config;

public class InvalidAgeException extends Exception {
	private Integer age;
	
	public InvalidAgeException(Integer age) {
		super("Age cannot be under " + Config.MIN_AGE + " or negative, you entered: "  + age); //super refers to the Exception class constructor
		this.age = age;
	}
	
	public Integer getAge() {
		return age;
	}
}
