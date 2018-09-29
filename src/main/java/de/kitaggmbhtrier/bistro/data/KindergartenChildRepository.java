package de.kitaggmbhtrier.bistro.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface KindergartenChildRepository extends CrudRepository<KindergartenChild, Long> {
    List<KindergartenChild> findByLastName(String lastName);
    Optional<KindergartenChild> findByLastNameAndFirstName(String firstName, String lastName);
    List<KindergartenChild> findByGroupName(String groupName);
}
