package de.kitaggmbhtrier.bistro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEAL")
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private KindergartenChild child;
	private String groupName;
	private MealType type;
	private Date mealDate;
	private boolean eaten;
	
	protected Meal() {}
	
	public Meal(Date mealDate, MealType type, KindergartenChild child) {
		this.mealDate = mealDate;
		this.type = type;
		this.child = child;
		this.groupName = child.getGroup().getName();
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public KindergartenChild getChild() {
		return child;
	}

	public MealType getType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("Meal[id=%d, type=%s]", id, this.type.getName());
	}

	public Long getId() {
		return id;
	}
	
}
