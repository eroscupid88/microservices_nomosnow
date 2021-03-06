package ca.nomosnow.sport_event_service.model;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


/**
 * sportEvent model create sport event
 */
// using JPA :data persisted to database
@Getter @Setter @ToString
@Entity
@Table(name = "sportevent")
public class SportEvent extends RepresentationModel<SportEvent> {
    @Id
    @Column(name = "sportevent_id",nullable = false)
    private String sportEventId;
    @Column(name = "organization_id" ,nullable = false)
    private String organizationId;
    @Column(name ="organization_name" , nullable= false)
    private String organizationName;
    @Column(name ="event_name" , nullable= false)
    private String eventName;
    @Column(name ="event_type" , nullable= false)
    private String eventType;
    @Column(name ="event_location" , nullable= false)
    private String eventLocation;
    @Column(name ="comment" , nullable= true)
    private String comment;

    public SportEvent withComment(String comment) {
        this.setComment(comment);
        return this;
    }



}
