package io.lassomarketing.redirectsdemo.service;

import io.lassomarketing.redirectsdemo.entity.Partner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service imitates acquiring the list of our partners we want our user redirect to at the moment
 *
 * @author David Garibov
 */
@Service
public class PartnersService {
    public List<Partner> getPartners() {
        return List.of(
                new Partner("partner1", "http://localhost:8080/partner1","{USER_ID}"),
                new Partner("partner2", "http://localhost:8080/partner2","{USR}"),
                new Partner("partner3", "http://localhost:8080/partner3","{ID}")

        );
    }
}
