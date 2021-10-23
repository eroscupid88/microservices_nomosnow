package ca.nomosnow.api.events;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Getter
@Setter
public class SportEventDetails {

    private Long consumerId;
    private Long sportOrganizationId;

    public SportEventDetails() {

    }

    public SportEventDetails(Long consumerId, Long sportOrganizationId) {
        this.consumerId = consumerId;
        this.sportOrganizationId = sportOrganizationId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
