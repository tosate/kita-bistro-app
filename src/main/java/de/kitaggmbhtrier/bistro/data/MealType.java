package de.kitaggmbhtrier.bistro.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEAL_TYPE")
public class MealType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	protected MealType() {}
	
	public MealType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("MealType[id=%d, name='%s']", id, name);
	}

	public String getName() {
		return name;
	}
	
}
