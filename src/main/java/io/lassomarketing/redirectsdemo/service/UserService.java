package io.lassomarketing.redirectsdemo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * This service imitates aquiring our user's id (or any other attribute) we would like to piggyback over the redirects.
 * Normally you could get them from session object or any other legit source, but for the demo we would just generate it
 *
 * @author David Garibov
 */
@Service
public class UserService {
    public String getUserId() {
        return UUID.randomUUID().toString();
    }
}
