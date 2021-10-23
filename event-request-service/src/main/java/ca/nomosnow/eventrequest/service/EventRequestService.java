package ca.nomosnow.eventrequest.service;

import ca.nomosnow.api.events.EventRequestDomainEvent;
import ca.nomosnow.api.events.SportEventDetails;
import ca.nomosnow.eventrequest.domain.SportOrganizationEventCreateDepartmentNotFoundException;
import ca.nomosnow.eventrequest.domain.model.EventRequest;
import ca.nomosnow.eventrequest.domain.model.SportOrganizationEventCreateDepartment;
import ca.nomosnow.eventrequest.events.EventRequestServiceEventPublisher;
import ca.nomosnow.eventrequest.repository.EventRequestRepository;
import ca.nomosnow.eventrequest.repository.SportOrganizationEventCreateDepartmentRepository;
import ca.nomosnow.eventrequest.sagas.createeventrequest.CreateEventRequestSagaData;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class EventRequestService {
    static final Logger logger = LoggerFactory.getLogger(EventRequestService.class);

    private EventRequestRepository eventRequestRepository;
    private SportOrganizationEventCreateDepartmentRepository sportOrganizationEventCreateDepartmentRepository;
    private SagaManager<CreateEventRequestSagaData> createEventRequestSagaManager;
    private EventRequestServiceEventPublisher eventRequestServiceEventPublisher;

    public EventRequestService(EventRequestRepository eventRequestRepository,
                               SportOrganizationEventCreateDepartmentRepository sportOrganizationEventCreateDepartmentRepository,
                               SagaManager<CreateEventRequestSagaData> createEventRequestSagaManager,
                               EventRequestServiceEventPublisher eventRequestServiceEventPublisher) {
        this.eventRequestRepository = eventRequestRepository;
        this.sportOrganizationEventCreateDepartmentRepository =  sportOrganizationEventCreateDepartmentRepository;
        this.createEventRequestSagaManager = createEventRequestSagaManager;
        this.eventRequestServiceEventPublisher = eventRequestServiceEventPublisher;
    }
    public EventRequest createEventRequest(Long consumerId, Long sportOrganizationEventCreateDepartmentId) {
        SportOrganizationEventCreateDepartment sportOrganizationEventCreateDepartment = sportOrganizationEventCreateDepartmentRepository.findById(sportOrganizationEventCreateDepartmentId).orElseThrow(() -> new SportOrganizationEventCreateDepartmentNotFoundException(sportOrganizationEventCreateDepartmentId));

        ResultWithDomainEvents<EventRequest, EventRequestDomainEvent> eventRequestAndEvents = EventRequest.createEventRequest(consumerId, sportOrganizationEventCreateDepartment);

        EventRequest eventRequest = eventRequestAndEvents.result;

        eventRequestRepository.save(eventRequest);                                                                                                                   // insert the new order into database.

        eventRequestServiceEventPublisher.publish(eventRequest, eventRequestAndEvents.events);                                                                               // publish the domain event
        logger.debug("Send EventRequestCreatedEvent to EventRequest event channel");

        SportEventDetails sportEventDetails = new SportEventDetails(consumerId, sportOrganizationEventCreateDepartmentId);

        CreateEventRequestSagaData data = new CreateEventRequestSagaData(eventRequest.getId(), sportEventDetails);
        createEventRequestSagaManager.create(data,EventRequest.class,eventRequest.getId());

        // instantiates the saga orchestrator,
        // then the saga orchestrator sends a command message to the first saga participant,
        // inserts the saga orchestrator in the database.


        return eventRequest;
    }
}
