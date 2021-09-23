package ca.nomosnow.sportorganizationservice.model;

import ca.nomosnow.sportorganizationservice.config.ConfigService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sporteventorganization")
public class SportOrganization {
    @Id
    @Column(name = "organization_id",nullable = false)
    private String id;
    @Column(name ="name",nullable = false)
    private String sportOrganizationName;
    @Column(name = "email",nullable = false)
    private String sportOrganizationEmail;
    @Column(name = "phone",nullable = false)
    private String sportOraganizationPhone;
    @Transient
    private String comment;

    public SportOrganization withComment(String comment) {
        this.comment = comment;
        return this;
    }


}
