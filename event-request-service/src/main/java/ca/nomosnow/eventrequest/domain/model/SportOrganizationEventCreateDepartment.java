package ca.nomosnow.eventrequest.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class SportOrganizationEventCreateDepartment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;



}
