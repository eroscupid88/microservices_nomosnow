package ca.nomosnow.sportorganizationeventcreatedepartmentservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sportevents")
public class SportEvent {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    SportEventState currentState;
    SportEventState previousState;

    private LocalDateTime eventRegistrationCreate;
    private LocalDateTime eventRegistrationDeadline;
    private LocalDateTime eventPreparePeriod;
    private LocalDateTime EventMainStart;
    private LocalDateTime EventMainEnd;
    public SportEvent() {
    }

}
