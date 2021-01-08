package io.lassomarketing.redirectsdemo.service;

import io.lassomarketing.redirectsdemo.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * @author David Garibov
 */
public class PixelServiceTest {
    private final PixelService pixelService = new PixelService();

    public PixelServiceTest() throws Exception {
    }

    @Test
    public void testBuildRedirectTag() {
        Partner partner = new Partner("partner1", "http://localhost:8080/partner1", "{USER_ID}");
        String ourUserId = "some-user-id";
        String redirectTag = pixelService.buildRedirectTag(partner, ourUserId, "http://localhost:8080/identify/store");
        Assertions.assertEquals("<img width=\"1\" height=\"1\" src=\"http://localhost:8080/partner1?http://localhost:8080/identify/store?partnerName=partner1&ourId=some-user-id&partnerUserId=%7BUSER_ID%7D\" />",
                redirectTag);
    }
}
