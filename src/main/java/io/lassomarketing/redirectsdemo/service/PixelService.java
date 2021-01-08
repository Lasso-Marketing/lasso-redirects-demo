package io.lassomarketing.redirectsdemo.service;

import io.lassomarketing.redirectsdemo.entity.Partner;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static io.lassomarketing.redirectsdemo.controller.IdentityRedirectsController.*;


@Service
public class PixelService {

    private final String pixelTemplate;

    public PixelService() throws Exception {

        Path path = Paths.get(ClassLoader.getSystemResource("templates/pixel.js").toURI());
        this.pixelTemplate = Files.readString(path);
    }

    /**
     * Forms javascript code that will run all redirects for our users.
     * For every partner that we want to sync with, will be formed an img tag, that will be placed to the page by the code
     * from the template and fire a call
     *
     * @param partners list of partners that we're going to sync our users id with
     * @param ourUserId our user id that will be piggybacked back to us along with id for the same user from our partner
     * @param redirectBackUrl our endpoint for storing user's data
     * @return js code
     */
    public byte[] buildPixel(List<Partner> partners, final String ourUserId, final String redirectBackUrl) {
        return String.format(pixelTemplate,
                partners.stream().map(partner -> buildRedirectTag(partner, ourUserId, redirectBackUrl))
                        .collect(Collectors.joining(""))
        ).getBytes();
    }

    String buildRedirectTag(Partner partner,
                            String ourUserId,
                            String redirectBackUri) {
        String redirectToPartnerUrl = null;
        try {
            redirectToPartnerUrl = UriComponentsBuilder.fromHttpUrl(partner.getEndpointUrl())
                    .query(buildRedirectBackFullUrl(partner.getName(), partner.getUserIdPlaceHolder(), ourUserId, redirectBackUri))
                    .build()
                    .toUri()
                    .toURL().toString();
        } catch (Exception ignored) {
        }
        return "<img width=\"1\" height=\"1\" src=\"" + redirectToPartnerUrl + "\" />";
    }

    private String buildRedirectBackFullUrl(String partnerName,
                                            String partnerUserIdPlaceholder,
                                            String ourUserId,
                                            String redirectBackUrl) {
        return redirectBackUrl
                + "?" + STORE_IDENTITY_RECORD_PARTNER_NAME_PARAM + "=" + partnerName
                + "&" + STORE_IDENTITY_RECORD_OUR_USER_ID_PARAM + "=" + ourUserId
                + "&" + STORE_IDENTITY_RECORD_PARTNER_USER_ID_PARAM + "=" + partnerUserIdPlaceholder;
    }
}
