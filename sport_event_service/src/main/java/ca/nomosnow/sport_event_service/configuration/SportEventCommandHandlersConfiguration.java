package ca.nomosnow.sport_event_service.configuration;
import ca.nomosnow.shareresource.common.CommonConfiguration;
import ca.nomosnow.sport_event_service.service.SportEventCommandHandler;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
@Import({SagaParticipantConfiguration.class,TramEventsPublisherConfiguration.class, CommonConfiguration.class})
public class SportEventCommandHandlersConfiguration {

    @Bean
    public SportEventCommandHandler sportEventCommandHandler() {
        return new SportEventCommandHandler();
    }
    @Bean
    public SagaCommandDispatcher orderCommandHandlersDispatcher(SportEventCommandHandler sportEventCommandHandler, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("example", sportEventCommandHandler.commandHandlers());
    }
}
