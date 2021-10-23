package ca.nomosnow.eventrequest.repository;

import ca.nomosnow.eventrequest.domain.model.EventRequest;
import org.springframework.data.repository.CrudRepository;
public interface EventRequestRepository extends CrudRepository<EventRequest, Long> {
}
