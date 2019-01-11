package de.kitaggmbhtrier.bistro.data;

public enum MealType {
	BREAKFAST("Frühstück"),
	LUNCH("Mittagessen");
	
	private String name;
	
	MealType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static MealType findByName(String name) {
		for(MealType mealType : MealType.values()) {
			if(mealType.getName().equals(name)) {
				return mealType;
			}
		}
		return null;
	}
}
