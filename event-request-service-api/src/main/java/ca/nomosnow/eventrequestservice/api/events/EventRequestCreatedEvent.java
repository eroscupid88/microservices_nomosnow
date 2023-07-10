package ca.nomosnow.eventrequestservice.api.events;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequestCreatedEvent implements EventRequestDomainEvent {

    SportEventDetails details;

    public EventRequestCreatedEvent(SportEventDetails details) {
        this.details = details;
    }
}
