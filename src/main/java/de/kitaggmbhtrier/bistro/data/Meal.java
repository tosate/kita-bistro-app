package de.kitaggmbhtrier.bistro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	private MealType type;
	private Date mealDate;
	private boolean eaten;
	
	protected Meal() {}
	
	public Meal(Date mealDate, MealType type, KindergartenChild child) {
		this.mealDate = mealDate;
		this.type = type;
		this.child = child;
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

	@Override
	public String toString() {
		return String.format("Meal[id=%d]", id);
	}
}
