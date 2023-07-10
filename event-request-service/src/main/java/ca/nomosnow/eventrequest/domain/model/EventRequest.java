package ca.nomosnow.eventrequest.domain.model;

import ca.nomosnow.eventrequestservice.api.events.EventRequestAuthorized;
import ca.nomosnow.eventrequestservice.api.events.EventRequestCreatedEvent;
import ca.nomosnow.eventrequestservice.api.events.EventRequestDomainEvent;
import ca.nomosnow.eventrequestservice.api.events.SportEventDetails;
import ca.nomosnow.common.UnsupportedStateTransitionException;
import ca.nomosnow.eventrequest.domain.EventRequestState;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static ca.nomosnow.eventrequest.domain.EventRequestState.APPROVED;
import static java.util.Collections.singletonList;

@Entity
@Getter
@Setter
@Table(name="eventrequest")
public class EventRequest {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private EventRequestState eventRequestState;

    private Long consumerId;

    private Long SportOrganizationEventCreateDepartmentId;

    public EventRequest() {
    }

    public EventRequest(Long consumerId, Long sportOrganizationEventCreateDepartmentId) {
        this.consumerId = consumerId;
        this.SportOrganizationEventCreateDepartmentId = sportOrganizationEventCreateDepartmentId;
        this.eventRequestState = EventRequestState.APPROVAL_PENDING;
    }
    public static ResultWithDomainEvents<EventRequest, EventRequestDomainEvent> createEventRequest(long consumerId, SportOrganizationEventCreateDepartment sportOrganizationEventCreateDepartment) {
        EventRequest eventRequest = new EventRequest(consumerId, sportOrganizationEventCreateDepartment.getId());
        List<EventRequestDomainEvent> events = singletonList(new EventRequestCreatedEvent(
                new SportEventDetails(consumerId, sportOrganizationEventCreateDepartment.getId())));
        return new ResultWithDomainEvents<>(eventRequest, events);
    }

    public List<EventRequestDomainEvent> noteApproved() {
        switch (eventRequestState) {
            case APPROVAL_PENDING:
                this.eventRequestState = APPROVED;
                return singletonList(new EventRequestAuthorized());
            default:
                // need some exception
                throw new UnsupportedStateTransitionException(eventRequestState);
        }
    }

}
