package ca.nomosnow.sport_event_service.repository;
import ca.nomosnow.sport_event_service.domain.model.SportEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SportEventRepository extends CrudRepository<SportEvent, String> {
    SportEvent findByOrganizationIdAndSportEventId(String organizationId,String sportEventId);
    List<SportEvent> findByOrganizationId(String organizationId);
}
