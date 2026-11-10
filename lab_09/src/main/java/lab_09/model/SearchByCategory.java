package lab_09.model;

public class SearchByCategory implements SearchStrategy<ElectronicDevice> {
	private ECategory category;

	public SearchByCategory(ECategory category) {
		this.category = category;
	}

	@Override
	public boolean matches(ElectronicDevice item) {
		return item.getCategory() == category;
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
}
