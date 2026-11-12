package lab_09.model;

public class ElectronicDevice extends Item {
	private ECategory category;
	private String brand;
	
	public ElectronicDevice(int id, String name, String brand, ECategory category) {
		super(id, name);
		setBrand(brand);
		setCategory(category);
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		if (brand == null || brand.isEmpty()) {
			throw new IllegalArgumentException("Brand cannot be null or empty");
		}
		this.brand = brand;
	}
	
	public ECategory getCategory() {
		return category;
	}
	public void setCategory(ECategory category) {
		if (category == null) {
			throw new IllegalArgumentException("Category cannot be null");
		}
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "ElectronicDevice [id=" + getId() + ", name=" + getName() + ", brand=" + brand + ", category=" + category + "]";
	}
}
