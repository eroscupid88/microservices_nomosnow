package ca.nomosnow.eventrequest.sagas.createeventrequest;

import ca.nomosnow.eventrequestservice.api.events.SportEventDetails;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Getter
@Setter
public class CreateEventRequestSagaData {
    private Long         eventRequestId;
    private long         eventId;
    private SportEventDetails sportEventDetails;

    public CreateEventRequestSagaData(Long eventRequestId, SportEventDetails sportEventDetails) {
        this.eventRequestId      = eventRequestId;
        this.sportEventDetails = sportEventDetails;
    }
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
