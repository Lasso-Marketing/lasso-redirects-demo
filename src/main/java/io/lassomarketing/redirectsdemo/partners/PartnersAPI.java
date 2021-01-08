package io.lassomarketing.redirectsdemo.partners;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * This controller imitates API of our partners that we're redirecting users to.
 * They take a whole http query string as a parameter, substitute their user id into the placeholder and redirect futher.
 * Although they all share the same principle of work, they use different placeholders
 * to emphasize that they are different partners with their own API's.
 *
 * @author David Garibov
 */
@RestController
public class PartnersAPI {

    @GetMapping("/partner1")
    public RedirectView partner1RedirectTo(HttpServletRequest request) {
        String redirectBackString = URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
        return new RedirectView(redirectBackString.replaceAll("\\{USER_ID}", getUserId()));
    }

    @GetMapping("/partner2")
    public RedirectView partner2RedirectTo(HttpServletRequest request) {
        String redirectBackString = URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
        return new RedirectView(redirectBackString.replaceAll("\\{USR}", getUserId()));
    }

    @GetMapping("/partner3")
    public RedirectView partner3RedirectTo(HttpServletRequest request) {
        String redirectBackString = URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
        return new RedirectView(redirectBackString.replaceAll("\\{ID}", getUserId()));
    }

    private String getUserId() {
        return UUID.randomUUID().toString();
    }
}
