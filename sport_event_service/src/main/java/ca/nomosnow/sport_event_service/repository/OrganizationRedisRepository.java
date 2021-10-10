package ca.nomosnow.sport_event_service.repository;

import ca.nomosnow.sport_event_service.model.SportOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<SportOrganization,String>  {
}
