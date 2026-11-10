package psu.edu.se411.lab13.services;

import org.springframework.stereotype.Service;

import psu.edu.se411.lab13.model.Product;
import psu.edu.se411.lab13.repositories.ProductRepository;

@Service
public class ProductServices {

	private final ProductRepository productRepository;

	public ProductServices(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Iterable<Product> findAll() {
		return productRepository.findAll();s
	}
}
