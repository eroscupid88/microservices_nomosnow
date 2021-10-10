package ca.nomosnow.sport_event_service.model;


import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RedisHash("sporteventorganizations")
public class SportOrganization extends RepresentationModel<SportOrganization> {

    @Id
    private String id;
    private String sportOrganizationName;
    private String sportOrganizationEmail;
    private String sportOrganizationPhone;
}
