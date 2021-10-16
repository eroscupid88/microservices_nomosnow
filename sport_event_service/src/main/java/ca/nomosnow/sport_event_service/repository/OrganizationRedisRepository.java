package ca.nomosnow.sport_event_service.repository;

import ca.nomosnow.sport_event_service.domain.model.SportOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<SportOrganization,String>  {
}
