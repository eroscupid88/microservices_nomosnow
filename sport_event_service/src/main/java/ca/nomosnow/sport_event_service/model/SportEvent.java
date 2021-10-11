package ca.nomosnow.sport_event_service.model;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


/**
 * sportEvent model create sport event
 */
// using JPA :data persisted to database
@Getter @Setter @ToString
@Entity
@Table(name = "sportevents")
//@RedisHash("sportevents")
public class SportEvent extends RepresentationModel<SportEvent> {
    @Id
    @Column(name = "sportevent_id",nullable = false)
    private String sportEventId;
    @Column(name = "organization_id" ,nullable = false)
    private String organizationId;
    @Column(name ="event_name" , nullable= false)
    private String sportEventName;
    @Column(name ="comment" , nullable= true)
    private String comment;

    @Transient
    private String sportOrganizationName;
    @Transient
    private String sportOrganizationEmail;
    @Transient
    private String sportOrganizationPhone;

    public SportEvent withComment(String comment) {
        this.setComment(comment);
        return this;
    }



}
