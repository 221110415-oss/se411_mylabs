package edu.psu.se411.lab_practice.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Course(
	@Id Long id,
	@NotBlank(message= "Course code cannot be blank")
	String code,
	@NotBlank(message= "Course name cannot be blank")
	@Size(min=2, message= "Course name must be at least 2 characters long")
	String name,
	String description,
	@Min(value=1, message= "Course credits must be at least 1")
	@Max(value=5, message= "Course credits cannot be more than 5")
	Integer credits
) {}
