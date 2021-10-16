package ca.nomosnow.sport_event_service.redisCache;

import brave.ScopedSpan;
import brave.Tracer;
import ca.nomosnow.sport_event_service.domain.model.SportOrganization;
import ca.nomosnow.sport_event_service.repository.OrganizationRedisRepository;
import ca.nomosnow.sport_event_service.repository.SportEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheSupport {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheSupport.class);

    @Autowired
    OrganizationRedisRepository redisOrganizationRepository;
    @Autowired
    SportEventRepository redisSportEventRepository;
    @Autowired
    Tracer tracer;

    /**
     * Method checkRedisCache to check and return SportOrganization. return null if there is none
     * @param sportOrganizationId sport organization id
     * @return SportOrganization if found, null otherwise
     */
    public SportOrganization OrganizationCheckRedisCache(String sportOrganizationId) {
        ScopedSpan newSpan = tracer.startScopedSpan("readSportOrganizationDataFromRedis");

        try {
            return redisOrganizationRepository
                    .findById(sportOrganizationId)
                    .orElse(null);
        }catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization{} check Redis Cache.  Exception {}",
                    sportOrganizationId, ex);
            return null;
        }finally {
            newSpan.tag("peer.service", "redis");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
    }


    /**
     * cacheOrganizationObject method save SportOrganization Object into cache if there is any change
     * @param sportOrganization SportOrganization object
     */
    public void cacheOrganizationObject(SportOrganization sportOrganization) {
        try {
            redisOrganizationRepository.save(sportOrganization);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}",
                    sportOrganization.getId(), ex);
        }
    }

//    /**
//     * Method sportEventCheckRedisCache to check and return SportEvent. return null if there is none
//     * @param sportEventId sport organization id
//     * @return SportOrganization if found, null otherwise
//     */
//    public SportEvent sportEventCheckRedisCache(String sportOrganizationId, String sportEventId) {
//        try {
//            return redisSportEventRepository
//                    .findByOrganizationIdAndSportEventId(sportOrganizationId,sportEventId);
//        }catch (Exception ex){
//            logger.error("Error encountered while trying to retrieve sport event{} check Redis Cache.  Exception {}",
//                    sportEventId, ex);
//            return null;
//        }
//    }
//    /**
//     * cacheSportEventObject method save SportEvent Object into cache if there is any change
//     * @param sportEvent SportEvent object
//     */
//    public void cacheSportEventObject(SportEvent sportEvent) {
//        try {
//            redisSportEventRepository.save(sportEvent);
//        }catch (Exception ex){
//            logger.error("Unable to cache sport event {} in Redis. Exception {}",
//                    sportEvent.getSportEventId(), ex);
//        }
//    }



}
