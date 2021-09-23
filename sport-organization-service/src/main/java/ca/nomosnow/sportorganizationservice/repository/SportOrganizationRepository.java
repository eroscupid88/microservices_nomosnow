package ca.nomosnow.sportorganizationservice.repository;

import ca.nomosnow.sportorganizationservice.model.SportOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SportOrganizationRepository extends CrudRepository<SportOrganization, String> {
    public SportOrganization findSportOrganizationById(String sportOrganizationId);
}
