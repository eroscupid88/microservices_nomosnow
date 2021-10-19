package ca.nomosnow.sportorganizationeventcreatedepartmentservice.repository;

import ca.nomosnow.sportorganizationeventcreatedepartmentservice.model.SportOrganizationDraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



// SportOrganizationRepository
@Repository
public interface SportOrganizationRepository extends CrudRepository<SportOrganizationDraft, String> {
}
