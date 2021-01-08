package io.lassomarketing.redirectsdemo.controller;

import io.lassomarketing.redirectsdemo.service.PartnersService;
import io.lassomarketing.redirectsdemo.service.PixelService;
import io.lassomarketing.redirectsdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author David Garibov
 */
@RestController
@Slf4j
public class IdentityRedirectsController {

    public static final String IDENTITY_ENDPOINT_GROUP_URI = "/identity";
    public static final String STORE_IDENTITY_RECORD_ENDPOINT_URI = "/store";
    public static final String STORE_IDENTITY_RECORD_PARTNER_NAME_PARAM = "partnerName";
    public static final String STORE_IDENTITY_RECORD_PARTNER_USER_ID_PARAM = "partnerUserId";
    public static final String STORE_IDENTITY_RECORD_OUR_USER_ID_PARAM = "ourId";

    private final PixelService pixelService;
    private final PartnersService partnersService;
    private final UserService userService;

    public IdentityRedirectsController(PixelService pixelService, PartnersService partnersService, UserService userService) {
        this.pixelService = pixelService;
        this.partnersService = partnersService;
        this.userService = userService;
    }

    /**
     * This controller illustrates the mechanics of the multiple parallel http redirects using a single <script> tag,
     * embedded into the html page your users interact with.
     *
     * Pointing src attribute of that tag to this endpoint
     * will embed executable javascript code into the html, that will in its turn embed multiple img tags for every partner
     * you're going to exchange users data with, firing a call to their endpoint with all the parameters we need, generated in the
     * moment of the call and piggybacked back to our /store endpoint
     *
     * @param request http request object, used for building redirect-back url
     * @return executable javascript code
     */
    @GetMapping(produces = "application/javascript", value = IDENTITY_ENDPOINT_GROUP_URI)
    public byte[] getTag(HttpServletRequest request) {
        return pixelService.buildPixel(partnersService.getPartners(), userService.getUserId(), request.getRequestURL() + "/store");
    }

    /**
     * Endpoint our users being redirected from our partners api endpoint.
     * This request already contains user-mapping data between our and the partner's services
     *
     * @param partnerName -
     * @param partnerUserId user id from our partner's system
     * @param ourId user id from our system
     * @return HTTP 200 code
     */
    @GetMapping(value = IDENTITY_ENDPOINT_GROUP_URI + STORE_IDENTITY_RECORD_ENDPOINT_URI)
    public ResponseEntity<String> storeUserMapping(@RequestParam(name = STORE_IDENTITY_RECORD_PARTNER_NAME_PARAM) String partnerName,
                                           @RequestParam(name = STORE_IDENTITY_RECORD_PARTNER_USER_ID_PARAM) String partnerUserId,
                                           @RequestParam(name = STORE_IDENTITY_RECORD_OUR_USER_ID_PARAM) String ourId) {
        log.info("Our user id " + ourId + " is now mapped with " + partnerUserId
                + " id for our partner " + partnerName);
        return ResponseEntity.ok("OK");
    }
}
