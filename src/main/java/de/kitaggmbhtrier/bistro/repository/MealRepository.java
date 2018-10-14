package de.kitaggmbhtrier.bistro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.kitaggmbhtrier.bistro.data.Meal;
import de.kitaggmbhtrier.bistro.data.MealType;

public interface MealRepository extends CrudRepository<Meal, Long> {
	List<Meal> findByMealDate(Date mealDate);
	List<Meal> findByMealDateAndType(Date mealDate, MealType type);
}
