package ca.nomosnow.eventrequest.domain.model;

import ca.nomosnow.common.Address;
import ca.nomosnow.common.Money;
import ca.nomosnow.common.Sport;
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

    @Embedded
    private Money money = new Money(Integer.MAX_VALUE);
    @Embedded
    private Sport sport;
    @Embedded
    private Address address;


    public EventRequest() {

    }

}
