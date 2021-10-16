package ca.nomosnow.sport_event_service.sagaspaticipants;
import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SportEventCommand implements Command {
    private String sportEventId;

    protected SportEventCommand() {
    }

    protected SportEventCommand(String sportEventId) {
        this.sportEventId = sportEventId;
    }
}
