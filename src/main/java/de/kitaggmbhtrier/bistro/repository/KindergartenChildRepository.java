package de.kitaggmbhtrier.bistro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;

public interface KindergartenChildRepository extends CrudRepository<KindergartenChild, Long> {
    List<KindergartenChild> findByLastName(String lastName);
    Optional<KindergartenChild> findByLastNameAndFirstName(String firstName, String lastName);
    List<KindergartenChild> findByGroupName(String groupName);
}
