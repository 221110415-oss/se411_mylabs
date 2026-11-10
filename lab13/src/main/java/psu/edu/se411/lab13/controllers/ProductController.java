package psu.edu.se411.lab13.controllers;

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

}
