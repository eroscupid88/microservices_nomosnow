package ca.nomosnow.sportorganizationeventcreatedepartmentservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sporteventorganizations")
public class SportOrganizationDraft {
    @Id
    @Column(name = "organization_id",nullable = false)
    private String id;
    @Column(name ="name",nullable = false)
    private String sportOrganizationName;
    @Column(name = "email",nullable = false)
    private String sportOrganizationEmail;
    @Column(name = "phone",nullable = false)
    private String sportOraganizationPhone;
    @Column(name = "comment",nullable = true)
    private String comment;

    public SportOrganizationDraft withComment(String comment) {
        this.comment = comment;
        return this;
    }


}