package ca.nomosnow.sportorganizationservice.repository;

import ca.nomosnow.sportorganizationservice.model.SportOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SportOrganizationRepository extends CrudRepository<SportOrganization, String> {
    @Override
    Optional< SportOrganization> findById(String id);
}
