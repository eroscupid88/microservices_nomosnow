package ca.nomosnow.sportorganizationservice.service;

import brave.ScopedSpan;
import brave.Tracer;
import ca.nomosnow.sportorganizationservice.SportOrganizationServiceApplication;
import ca.nomosnow.sportorganizationservice.config.ConfigService;
import ca.nomosnow.sportorganizationservice.model.SportOrganization;
import ca.nomosnow.sportorganizationservice.repository.SportOrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SportOrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(SportOrganizationService.class);

    @Autowired
    ConfigService configService;
    @Autowired
    SportOrganizationRepository sportOrganizationRepository;
    @Autowired
    Tracer tracer;
    /**
     * findById method to find SportOrganization Object
     * @param sportOrganizationId String id
     * @return one or null if not found
     */
    public SportOrganization getSportOrganization(String sportOrganizationId) {
        Optional<SportOrganization> opt = null;
        ScopedSpan newSpan = tracer.startScopedSpan("getOrgDBCall");
        try {
            opt = sportOrganizationRepository.findById(sportOrganizationId);
            if (opt.isEmpty()) {
                String message = String.format("Unable to find an organization with the Organization id %s", sportOrganizationId);
                logger.error(message);
                throw new IllegalArgumentException(message);
            }logger.debug("Retrieving Organization Info: " + opt.get().toString());
            }finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
        return opt.get();
    }

    /**
     * createSportOrganization method to generate random Id and save it into repository
     * @param sportOrganization SportOrganization object
     * @return SportOrganization
     */
    public SportOrganization createSportOrganization(SportOrganization sportOrganization) {
        sportOrganization.setId(UUID.randomUUID().toString());
        sportOrganizationRepository.save(sportOrganization);
        return sportOrganization.withComment(configService.getProperty());
    }

    /**
     * Update sportOrganization method
     * @param sportOrganization SportOrganization
     */
    public SportOrganization updateSportOrganization(SportOrganization sportOrganization) {
        sportOrganizationRepository.save(sportOrganization);
        return sportOrganization.withComment(configService.getProperty());
    }

    /**
     * delete SportOrganization method
     * @param sportOrganization
     */
    public void deleteSportOrganization(SportOrganization sportOrganization) {
        sportOrganizationRepository.deleteById(sportOrganization.getId());

    }
}
