package ca.nomosnow.eventrequest.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="sportorganizationeventcreatedepartment")
public class SportOrganizationEventCreateDepartment {
    @Id
    private Long id;



}
