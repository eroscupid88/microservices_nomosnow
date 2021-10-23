package ca.nomosnow.eventrequest.events;

import ca.nomosnow.api.events.EventRequestDomainEvent;
import ca.nomosnow.eventrequest.domain.model.EventRequest;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class EventRequestServiceEventPublisher extends AbstractAggregateDomainEventPublisher<EventRequest, EventRequestDomainEvent> {
    public EventRequestServiceEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher,EventRequest.class,EventRequest::getId);
    }
}
