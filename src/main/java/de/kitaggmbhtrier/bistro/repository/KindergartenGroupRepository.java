package de.kitaggmbhtrier.bistro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.kitaggmbhtrier.bistro.data.KindergartenGroup;

public interface KindergartenGroupRepository extends CrudRepository<KindergartenGroup, Long> {
	Optional<KindergartenGroup> findByName(String name);
}
