package de.kitaggmbhtrier.bistro.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHILD_ATTRIBUTE")
public class ChildAttribute {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String classification;
	private String name;
	
	protected ChildAttribute() {}
	
	public ChildAttribute(String classification, String name) {
		this.classification = classification;
		this.name = name;
	}

	public String getClassification() {
		return classification;
	}

	public String getName() {
		return name;
	}
	
}
