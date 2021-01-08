package io.lassomarketing.redirectsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This controller has two endpoints that are redirecting a user from one to another.
 * At some point browser will stop this redirects loop with ERR_TOO_MANY_REDIRECTS
 *
 * @author David Garibov
 */
@RestController("/")
public class InfiniteLoopController {

    private static final String ENDPOINT_1 = "/endpoint1";
    private static final String ENDPOINT_2 = "/endpoint2";

    @GetMapping(ENDPOINT_1)
    public RedirectView endpoint1() {
        return new RedirectView(ENDPOINT_2);
    }

    @GetMapping(ENDPOINT_2)
    public RedirectView endpoint2() {
        return new RedirectView(ENDPOINT_1);
    }
}
