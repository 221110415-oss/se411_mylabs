package psu.edu.se411.lab13.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Positive;
import psu.edu.se411.lab13.model.Product;
import psu.edu.se411.lab13.services.ProductServices;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	private final ProductServices productService;

	public ProductController(ProductServices productService) {
		this.productService = productService;
	}

	@GetMapping()
	private ResponseEntity<Iterable<Product>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/{id}")
	private ResponseEntity<Product> findById(@PathVariable @Positive Integer id) {
		return productService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}
