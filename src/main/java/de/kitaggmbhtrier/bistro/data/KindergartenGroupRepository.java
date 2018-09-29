package de.kitaggmbhtrier.bistro.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface KindergartenGroupRepository extends CrudRepository<KindergartenGroup, Long> {
	Optional<KindergartenGroup> findByName(String name);
}
