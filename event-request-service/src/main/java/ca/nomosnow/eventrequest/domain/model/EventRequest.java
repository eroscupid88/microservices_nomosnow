package ca.nomosnow.eventrequest.domain.model;

import ca.nomosnow.eventrequest.domain.EventRequestState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private EventRequestState state;

    private Long comsumerId;

    private Long SportOrganizationEventCreateDepartmentId;

    public EventRequest() {

    }

}
