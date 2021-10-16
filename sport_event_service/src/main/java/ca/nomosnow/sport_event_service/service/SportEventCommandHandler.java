package ca.nomosnow.sport_event_service.service;

import ca.nomosnow.sport_event_service.sagaspaticipants.SportEventCommand;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class SportEventCommandHandler {
    @Autowired
    SportEventService sportEventService;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder.fromChannel("sport_event_channel").onMessage(SportEventCommand.class,this::sportEvent).build();
    }
    public Message sportEvent(CommandMessage<SportEventCommand> cm) {
        String sportEventId = cm.getCommand().getSportEventId();
        sportEventService.approved(sportEventId);
        return withSuccess();
    }

}
