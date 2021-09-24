package ca.nomosnow.sport_event_service.model;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class SportOrganization extends RepresentationModel<SportOrganization> {
    private String id;
    private String sportOrganizationName;
    private String sportOrganizationEmail;
    private String sportOrganizationPhone;
}
